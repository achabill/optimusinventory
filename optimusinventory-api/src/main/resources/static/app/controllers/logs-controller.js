'use strict';

optimusInventoryApp
    .controller('LogsController', ['UserService', "$location", function (userService, $location) {
        var self = this;

        self.isError = false;
        self.isSuccess = false;

        self.verifyAdmin = function () {
            userService.getAllUsers().then(function () {
            }, function (error) {
                if (error.data.message == "Not enough privileges to perform action") {
                    $location.path("/");
                }
            });
        };

        self.resetError = function () {
            self.isError = false;
        };
        self.resetSuccess = function () {
            self.isSuccess = false;
        };

        self.showDate = function () {
            console.log(self.date);
        }
        self.verifyAdmin();


    }]);