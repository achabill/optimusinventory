'use strict';

optimusInventoryApp
    .controller('LogsController', ['UserService', "$location", function (userService, $location) {
        var self = this;
        self.verifyAdmin = function () {
            userService.getAllUsers().then(function () { }, function (error) {
                if (error.data.message == "Not enough privileges to perform action") {
                    $location.path("/");
                }
            });
        };

        self.verifyAdmin();
    }]);