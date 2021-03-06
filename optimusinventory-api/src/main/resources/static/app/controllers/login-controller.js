'use strict';

optimusInventoryApp
    .controller('LoginController', ['UserService', "$location", "$rootScope", function (userService, $location, $rootScope) {
        console.log("LoginController init");


        var self = this;
        self.isError = false;
        self.isSuccess = false;
        self.errorMessage;

        self.login = function () {
            userService.login(self.user).then(function () {
                $rootScope.user = self.user;
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