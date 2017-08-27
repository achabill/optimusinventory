'use strict';

optimusInventoryApp
    .factory('MachineItemsService', ['$http', '$q', "UserService", "MachineService", function ($http, $q, userService, machineService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/machineitems";

        var service = {
            token: userService.token,
            getAllMachineItems: function () {
                return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getMachineItemById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            addMachineItem: function (item) {
                return $http.post(baseEndPoint + '/?token=' + service.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateMachineItemById: function (item, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + service.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            deleteMachineItemById: function (id) {
                return $http.delete(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    console.log(resposne);
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getAllMachines: function () {
                return machineService.getAllMachines();
            }
        };
        return service;
    }]);