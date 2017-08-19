'use strict';

optimusInventoryApp
    .factory('MachineService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/machines";

        var service = {
        };
        return service;
    }]);