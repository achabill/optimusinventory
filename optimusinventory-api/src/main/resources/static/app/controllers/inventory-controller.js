optimusInventoryApp.controller('InventoryController', ['fileModel', 'InventoryService', function (fileModel, inventoryService) {
    var self = this;
    console.log("Inventory controller");
    self.success = false;
    self.error = false;

    self.getAllItems = function () {
        inventoryService.getAllItems().then(function (response) {
            self.allItems = [];
            for (var i = 0; i < response.data.length; i++)
                self.allItems.push(response.data[i]);
        }, function (error) {
            console.log(error.data.message);
            self.error = true;
            self.errorMessage = errro.data.message;
        });
    };
    self.postOneItem = function () {
        inventoryService.postOneItem(self.item).then(
            function (response) {
                self.allItems.push(response.data);
                self.success = true;
            },
            function (error) {
                console.log(error);
                self.error = true;
                self.errorMessage = errro.data.message;
            }
        );
    };
    self.postFile = function () {
        inventoryService.postFile(self.myFile).then(
            function (response) {
                self.allItems.push(response.data);
                self.success = true;
            },
            function (error) {
                console.log(error);
                self.error = true;
                self.errorMessage = errro.data.message;
            }
        );
    };

    self.getAllItems();
}]);