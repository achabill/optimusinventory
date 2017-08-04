optimusInventoryApp.controller('SalesController', ['InventoryService', 'SalesService', 'DebtorService', '$q',
    function (inventoryService, salesService, debtorService, $q) {
        var self = this;
        self.isSuccess = false;
        self.isError = false;
        self.allItems = [];
        self.allCartItems = [];
        self.allDebtors = [];
        self.totalCartSum = 0;
        self.newDebtor = {};
        self.oldDebtor = {};
        var recentSale = {};

        self.getAllItems = function () {
            inventoryService.getAllItems().then(function (response) {
                for (var i = 0; i < response.data.length; i++)
                    if (response.data[i].quantity > 0) {
                        self.allItems.push(response.data[i]);
                    }
            }, function (error) {
                self.isError = true;
                self.errorMessage = error.data.message;
            });
        };

        self.resetError = function () {
            self.isError = false;
        };
        self.resetSuccess = function () {
            self.isSuccess = false;
        };

        self.addToCart = function (item) {
            var cartItem = {
                "stockItem": item,
                "quantity": 1,
                "total": 0
            };

            for (var i = 0; i < self.allCartItems.length; i++) {
                if (item.name == self.allCartItems[i].stockItem.name &&
                    item.category == self.allCartItems[i].stockItem.category) {
                    return;
                }
            }
            self.allCartItems.push(cartItem);
            self.setCartItemPrice(self.allCartItems.length - 1);
        };

        self.setCartItemPrice = function (i) {
            self.allCartItems[i].total = self.allCartItems[i].quantity * self.allCartItems[i].stockItem.sellingPrice;
            self.totalCartSum = 0;
            for (var i = 0; i < self.allCartItems.length; i++) {
                self.totalCartSum += self.allCartItems[i].total;
            }
        }

        self.checkoutCart = function () {
            if (self.allCartItems.length == 0) {
                self.isError = true;
                self.errorMessage = "Cart is empty";
                return;
            }
            for (var i = 0; i < self.allCartItems.length; i++) {
                if (isNaN(self.allCartItems[0].total)) {
                    self.isError = true;
                    self.errorMessage = "Not enough " + self.allCartItems[i].stockItem.name + " in stock.";
                    return;
                }
            }
            var sale = {
                "date": new Date(),
                "cart": {
                    "cartItems": self.allCartItems,
                    "total": self.totalCartSum
                }
            };

            return salesService.postSale(sale).then(function (response) {
                self.isSuccess = true;
                self.successMessage = "Items sold successfully";
                recentSale = response.data;

                for (var i = 0; i < response.data.cart.cartItems.length; i++) {
                    var cartItem = response.data.cart.cartItems[i];
                    var item = cartItem.stockItem;
                    item.quantity = item.quantity - cartItem.quantity;

                    inventoryService.updateItemById(item, item.id).then(function (res) {
                        console.log("update item");
                        for (var i = 0; i < self.allItems.length; i++) {
                            if (self.allItems[i].id == res.data.id) {
                                self.allItems[i] = res.data;
                            }
                        }
                    }, function (error) {
                        self.isError = true;
                        self.errorMessage = error.data.message;
                    });
                }
                self.clearCart();
            }, function (error) {
                self.isError = true;
                self.errorMessage = error.data.message;
            });
        };

        self.removeCartItem = function (i) {
            self.allCartItems.splice(i, 1);
            self.totalCartSum = 0;
            for (var i = 0; i < self.allCartItems.length; i++) {
                self.totalCartSum += self.allCartItems[i].total;
            }
        };

        self.borrowOutNewDebtor = function () {
            self.checkoutCart().then(function () {
                self.newDebtor.sales = [recentSale];
                self.active = true;
                self.newDebtor.amount = recentSale.cart.total;
                self.newDebtor.date = new Date();

                debtorService.postDebtor(self.newDebtor).then(function (response) {
                    self.isSuccess = true;
                    self.successMessage = "Cart successfully borrowed out to Debtor, " + response.data.firstName;
                }, function (error) {
                    self.isError = true;
                    self.errorMessage = error.data.message;
                });
            }, function (error) {
                self.isError = true;
                self.errorMessage = "debtor checkout error";
            });
        };

        self.borrowOutOldDebtor = function () {
            self.checkoutCart().then(function () {
                self.oldDebtor.sales.push(recentSale);
                self.oldDebtor.amount += recentSale.cart.total;

                debtorService.postDebtor(self.oldDebtor).then(function (response) {
                    self.isSuccess = true;
                    self.successMessage = "Cart successfully borrowed out to Debtor, " + response.data.firstName;
                }, function (error) {
                    self.isError = true;
                    self.errorMessage = error.data.message;
                });
            }, function (error) {
                self.isError = true;
                self.errorMessage = "cannnot post debtor";
            });
        };

        self.setOldDebtor = function (debtor) {
            self.oldDebtor = debtor;
        }

        self.getAllDebtors = function () {
            debtorService.getAllDebtors().then(function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    self.allDebtors.push(response.data[i]);
                }
            }, function (error) {
                self.isError = true;
                self.errorMessage = error.data.message;
            });
        };

        self.clearCart = function () {
            self.allCartItems = [];
            self.totalCartSum = 0;
        };

        self.getAllItems();
        console.log(self.allDebtors);
        self.getAllDebtors();
    }]);