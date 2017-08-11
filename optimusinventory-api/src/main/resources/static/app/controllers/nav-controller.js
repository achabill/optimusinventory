optimusInventoryApp.controller('NavController', ['UserService', '$localStorage', '$location', function (userService, $localStorage, $location) {
    var self = this;
    self.user = {};
    self.user.username = userService.user.username;
    self.token = userService.token;

    self.logout = function () {
        userService.logout(self.token).then(function (response) {
            $localStorage.$default({
                user: null,
                token: null
            });
            $location.path("/");
        }, function (error) {
        });
    }
}]);