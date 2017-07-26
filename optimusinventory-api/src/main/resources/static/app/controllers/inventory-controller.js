optimusInventoryApp.controller('InventoryController', ['fileModel', 'InventoryService', function (fileModel, inventoryService) {
    var self = this;

    self.getAllItems = function () {
        inventoryService.getAllItems().then(function (response) {
            self.allItems = [];
            for (var i = 0; i < response.data.length; i++)
                self.allItems.push(response.data[i]);
        }, function (error) {
            console.log(error.data.message);
        });
    };
    self.postOneItem = function () {
        inventoryService.postOneItem(self.item).then(
            function (response) {
                self.allItems.push(response.data);
            },
            function (error) {
                console.log(error);
            }
        );
    };
    self.postFile = function () {
        inventoryService.postFile(self.myFile).then(
            function (response) {
                self.allItems.push(response.data);
            },
            function (error) {
                console.log(error);
            }
        );
    }
}]);