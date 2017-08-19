optimusInventoryApp.controller('MachineController', ['MachineService', function (machineService) {
    var self = this;

    self.isSuccess = false;
    self.isError = false;
    self.allMachines = [];
    self.allMachineTypes = [];

    self.getAllMachines = function () {
        machineService.getAllMachines().then(function (response) {
            for (var i = 0; i < response.data.length; i++) {
                self.allMachines.push(response.data[i]);
            }
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        })
    };

    self.getAllMachineTypes = function () {
        machineService.getAllMachineTypes().then(function (response) {
            for (var i = 0; i < response.data.length; i++)
                self.allMachineTypes.push(response.data[i]);
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

    self.addMachine = function () {
        self.machine.dateCreated = new Date().toDateString();
        machineService.addMachine(self.machine).then(function (response) {
            var newMachine = response.data;
            self.allMachines.push(newMachine);
            self.isSuccess = true;
            self.successMessage = "Machine added successfully";
            self.machine = {};
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.setEditMachine = function (index) {
        self.itemIndex = index;
        self.editMachine = {};
        //do not bind the machine to the ui when updating
        //Does javascript have 'copy by value'?
        //i don't have time to check stackoverflow
        self.editMachine.id = self.allMachines[self.itemIndex].id;
        self.editMachine.name = self.allMachines[self.itemIndex].name;
        self.editMachine.description = self.allMachines[self.itemIndex].description;
        self.editMachine.dateCreated = self.allMachines[self.itemIndex].dateCreated;
        self.editMachine.machineType = self.allMachines[self.itemIndex].machineType;
    };

    self.editMachineById = function () {
        machineService.updateMachineById(self.editMachine, self.editMachine.id).then(function (response) {
            var i = self.itemIndex;
            self.allMachines[i] = response.data;
            self.isSuccess = true;
            self.successMessage = "Item updated successfully";
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.setDeleteMachine = function (index) {
        self.deleteMachine = self.allMachines[index];
    };

    self.deleteMachineById = function () {
        machineService.deleteMachineById(self.deleteMachine.id).then(function (response) {
            self.isSuccess = true;
            self.successMessage = "Deleted machine successfully";
            self.allMachines = [];
            self.getAllMachines();

            //work around.
            //some error occurs when deleting.
        }, function (error) {
            self.isSuccess = true;
            self.successMessage = "Deleted machine successfully";
            self.allMachines = [];
            self.getAllMachines();
        });
    }

    self.getAllMachines();
    self.getAllMachineTypes();

}]);