optimusInventoryApp.controller('AdminInventoryController', ['InventoryService', function (inventoryService) {
    var self = this;
    self.isError = false;
    self.isSuccess = false;

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

    self.resetError = function () {
        self.isError = false;
    };
    self.resetSuccess = function () {
        self.isSuccess = false;
    };

    self.getAllItems();
}]);