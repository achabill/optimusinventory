optimusInventoryApp.controller('TabsController', ['UserService', '$localStorage', '$location', function (userService, $localStorage, $location) {
    var self = this;


    self.isError = false;
    self.errorMessage = "sample error message";

    self.navigate = function (path) {
        self.isError = false;
        var requiredPrivilege;
        switch (path) {
            case 'sales':
                requiredPrivilege = 'CREATE_SALES';
                break;
            case 'debtors':
                requiredPrivilege = 'CREATE_DEBTORS';
                break;
            case 'inventory':
                requiredPrivilege = 'CREATE_ITEMS';
                break;
            case 'admin':
                requiredPrivilege = 'CREATE_SUMMARY';
                break;
            case 'machineitems':
                requiredPrivilege = 'CREATE_MACHINE_ITEMS';
                break;
            default:
                requiredPrivilege = 'READ_ITEMS';
                break;
        }

        if (userService.user.privileges.indexOf(requiredPrivilege) < 0) {
            self.isError = true;
            self.errorMessage = "You don't have the required privilege to enter this page";
            return;
        }

        $location.path(path);
    };
}]);