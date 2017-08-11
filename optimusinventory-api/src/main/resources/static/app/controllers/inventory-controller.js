optimusInventoryApp.controller('InventoryController', ['InventoryService', function (inventoryService) {
    var self = this;
    //console.log("Inventory controller");
    self.isSuccess = false;
    self.isError = false;
    self.allItems = [];

    self.getAllItems = function () {
        //console.log("get all items from contorller");
        inventoryService.getAllItems().then(function (response) {
            for (var i = 0; i < response.data.length; i++)
                self.allItems.push(response.data[i]);
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

    self.postOneItem = function () {
        inventoryService.postOneItem(self.item).then(
            function (response) {
                var newItem = response.data;
                var isInList = false;
                for (var i = 0; i < self.allItems.length; i++) {
                    var _item = self.allItems[i];
                    if (_item.name == newItem.name && _item.category == newItem.category) {
                        isInList = true;
                        self.allItems[i] = newItem;
                        break;
                    }
                }
                if (!isInList) {
                    self.allItems.push(newItem);
                }

                self.item = {};
                self.isSuccess = true;
                self.successMessage = "Item added/updated successfully";
            },
            function (error) {
                self.isError = true;
                self.errorMessage = errro.data.message;
            }
        );
    };
    self.postFile = function () {
        inventoryService.postFile(self.file).then(
            function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    var newItem = response.data[i];
                    var isInList = false;
                    for (var i = 0; i < self.allItems.length; i++) {
                        var _item = self.allItems[i];
                        if (_item.name == newItem.name && _item.category == newItem.category) {
                            isInList = true;
                            self.allItems[i] = newItem;
                            break;
                        }
                    }
                    if (!isInList) {
                        self.allItems.push(newItem);
                    }
                }
                self.successMessage = "Items add/updated successfully";
                self.isSuccess = true;
            },
            function (error) {
                //console.log(error.data);
                self.isError = true;
                self.errorMessage = error.data.message;
            }
        );
    };

    self.setEditItem = function (index) {
        self.itemIndex = index;
        self.editItem = self.allItems[self.itemIndex];
    }

    self.editItemById = function () {
        inventoryService.updateItemById(self.editItem, self.editItem.id).then(
            function (response) {
                var i = self.itemIndex;
                self.allItems[i] = response.data;

                //console.log(response.data);
                self.isSuccess = true;
                self.successMessage = "Item updated successfully";
            }, function (error) {
                self.isError = true;
                self.errorMessage = error.data.message;
            }
        );
    };

    self.getAllItems();
}]);