optimusInventoryApp.controller('MachineItemsController', ['MachineItemsService', function (machineItemsService) {
    var self = this;
    self.allMachines = [];
    self.machineItem = {};
    self.allMachineItems = [];
    self.isError = false;
    self.isSuccess = false;
    self.errorMessage = "";
    self.successMessage = "";

    self.resetError = function () {
        self.isError = false;
    };
    self.resetSuccess = function () {
        self.isSuccess = false;
    };

    self.getAllMachineItems = function () {

        machineItemsService.getAllMachineItems().then(function (response) {
            self.allMachineItems = [];
            for (var i = 0; i < response.data.length; i++) {
                if (response.data[i].date === new Date().toDateString()) {
                    self.allMachineItems.push(response.data[i]);
                }
            }
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.getAllMachines = function () {
        machineItemsService.getAllMachines().then(function (response) {
            for (var i = 0; i < response.data.length; i++) {
                self.allMachines.push(response.data[i]);
            }
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.addMachineItem = function () {
        self.machineItem.total = self.machineItem.unitPrice * self.machineItem.quantity;
        self.machineItem.date = new Date().toDateString();

        machineItemsService.addMachineItem(self.machineItem).then(function (response) {
            self.allMachineItems.push(response.data);
            self.isSuccess = true;
            self.successMessage = "Added item successfully";
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.clearMachineItem = function () {
        self.machineItem = {};
    };

    self.getAllMachineItems();
    self.getAllMachines();
}]);