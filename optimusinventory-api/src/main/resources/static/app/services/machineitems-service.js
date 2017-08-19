'use strict';

optimusInventoryApp
    .factory('MachineItemsService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/machineitems";

        var service = {
        };
        return service;
    }]);