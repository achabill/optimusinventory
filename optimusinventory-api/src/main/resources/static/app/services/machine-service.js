'use strict';

optimusInventoryApp
    .factory('MachineService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/machines";

        var service = {
            token: userService.token,
            getAllMachines: function () {
                return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getAllMachineTypes: function () {
                return $http.get(baseEndPoint + '/types/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getMachineById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            addMachine: function (item) {
                return $http.post(baseEndPoint + '/?token=' + service.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateMachineById: function (item, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + service.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            deleteMachineById: function (id) {
                return $http.delete(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    console.log(resposne);
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);