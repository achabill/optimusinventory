'use strict';

optimusInventoryApp
    .controller('LoginController', ['LoginService', "$location", function(loginService, $location) {
        var self = this;
        self.user = null;
        self.login = function() {
            loginService.login(self.user).then(function() {
                    $location.path("/inventory");
                },
                function() {
                    self.errorMessage = loginService.error;
                });
        }
    }]);