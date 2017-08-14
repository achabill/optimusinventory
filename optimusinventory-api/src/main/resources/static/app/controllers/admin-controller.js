'use strict';

optimusInventoryApp
    .controller('AdminController', ['UserService', "$location", function (userService, $location) {


        var self = this;
        self.isError = false;
        self.isSuccess = false;

        self.verifyAdmin = function () {
            userService.getAllUsers().then(function () { }, function (error) {
            });
        };

        self.resetError = function () {
            self.isError = false;
        };
        self.resetSuccess = function () {
            self.isSuccess = false;
        };
        self.verifyAdmin();
    }]);