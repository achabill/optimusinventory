optimusInventoryApp.controller('UsersController', ['UserService', function (userService) {
    var self = this;
    self.user = {}
    self.error = false;
    self.success = false;

    self.getAllUsers = function () {
        userService.getAllUsers().then(function (response) {
            self.allUsers = [];
            for (var i = 0; i < response.data.length; i++) {
                response.data[i].createdOn = new Date(response.data[i].createdOn).toLocaleDateString();
                self.allUsers.push(response.data[i]);
            }
        }, function (error) {
            self.error = true;
            self.errorMessage = error.data.message;
        });
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
            self.error = true;
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
            self.success = true;
            self.allUsers.push(response.data);
        }, function (error) {
            self.error = true;
            self.errorMessage = error.data.message;
        });
    }

    self.getAllUsers();
    self.getAllPrivileges();
}]);