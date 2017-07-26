'use strict';

optimusInventoryApp.factory('NavbarService', ['UserService', '$http', '$q', function (userService, $http, $q) {
    console.log("navbar service");
    var service = {
        user: userService.user
    };
    console.log(service.user);
    return service;
}]);