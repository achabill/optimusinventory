optimusInventoryApp.controller('UsersController', ['UserService', function (userService) {
    var self = this;
    self.user = {}
    self.isError = false;
    self.isSuccess = false;
    self.allUsers = [];

    self.getAllUsers = function () {
        userService.getAllUsers().then(function (response) {

            for (var i = 0; i < response.data.length; i++) {
                response.data[i].createdOn = new Date(response.data[i].createdOn).toLocaleDateString();
                self.allUsers.push(response.data[i]);
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

    self.getAllPrivileges = function () {
        userService.getAllPrivileges().then(function (response) {
            self.allPrivileges = [];
            for (var i = 0; i < response.data.length; i++) {
                var privilege = {
                    "isChecked": false,
                    "value": response.data[i]
                };
                self.allPrivileges.push(privilege);
            }
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    validatePassword = function (password1, password2) {
        if (password1 == null || password2 == null) {
            return false;
        }
        if (password1 != password2) {
            return false;
        }
        return true;
    }

    self.addUser = function () {
        if (validatePassword(self.password1, self.password2) == false) {
            self.addUserError = "Passwords don't match";
            return;
        }
        self.user.password = self.password1;
        self.user.privileges = [];
        for (var i = 0; i < self.allPrivileges.length; i++) {
            if (self.allPrivileges[i].isChecked) {
                self.user.privileges.push(self.allPrivileges[i].value);
            }
        }
        self.user.createdOn = new Date();
        userService.addUser(self.user).then(function (response) {
            self.isSuccess = true;
            self.allUsers.push(response.data);
            self.successMessage = "User added successfully."
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    }

    self.getAllUsers();
    self.getAllPrivileges();
}]);