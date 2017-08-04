optimusInventoryApp.controller('DebtorsController', ['DebtorService', function (debtorService) {
    var self = this;
    self.isSuccess = false;
    self.isError = false;
    self.allDebtors = [];

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
        console.log("cart item onchanged");
        self.allCartItems[i].total = self.allCartItems[i].quantity * self.allCartItems[i].stockItem.sellingPrice;
        console.log(self.allCartItems[i]);
        self.totalCartSum = 0;
        for (var i = 0; i < self.allCartItems.length; i++) {
            self.totalCartSum += self.allCartItems[i].total;
        }
    };

    self.checkoutCart = function () {
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
                "cartItems": self.allCartItems
            }
        };


        self.getAllItems();
    };
}]);