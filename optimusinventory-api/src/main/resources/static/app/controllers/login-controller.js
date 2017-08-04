'use strict';

optimusInventoryApp
    .controller('LoginController', ['UserService', "$location", function (userService, $location) {
        console.log("LoginController init");


        var self = this;
        self.isError = false;
        self.isSuccess = false;

        self.login = function () {
            userService.login(self.user).then(function () {
                $location.path("/sales");
            }, function () {
                self.errorMessage = userService.error;
                self.isError = true;
            });
        };

        self.resetError = function () {
            self.isError = false;
        };
        self.resetSuccess = function () {
            self.isSuccess = false;
        };
    }]);