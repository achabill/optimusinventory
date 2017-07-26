optimusInventoryApp.controller('UsersController', ['UserService', function (userService) {
    var self = this;

    self.getAllUsers = function () {
        userService.getAllUsers().then(function (response) {
            self.users = [];
            for (var i = 0; i < response.data.length; i++)
                self.users.push(response.data[i]);
        }, function (error) {
            console.log(error.data.message);
        });
    };

    self.getAllUsers();
}]);