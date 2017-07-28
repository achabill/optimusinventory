optimusInventoryApp.controller('AdminInventoryController', ['InventoryService', function (inventoryService) {
    var self = this;
    self.error = false;

    self.getAllItems = function () {
        inventoryService.getAllItems().then(function (response) {
            self.allItems = [];
            for (var i = 0; i < response.data.length; i++) {
                self.allItems.push(response.data[i]);
            }
        }, function (error) {
            self.error = true;
            self.errorMessage = error.data.message;
        });
    };

    self.getAllItems();
}]);