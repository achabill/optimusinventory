'use strict';

optimusInventoryApp
    .factory('MachineService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/machines";

        var service = {
            getAllMachines: function () {
                return $http.get(baseEndPoint + '/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getAllMachineTypes: function () {
                return $http.get(baseEndPoint + '/types/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getAllMachineQualities: function () {
                return $http.get(baseEndPoint + '/qualities/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getMachineById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            addMachine: function (item) {
                return $http.post(baseEndPoint + '/?token=' + userService.token, item).then(function (response) {
                    console.log(response);
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateMachineById: function (item, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + userService.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            deleteMachineById: function (id) {
                return $http.delete(baseEndPoint + '/' + id + '/?token=' + userService.token).then(function (response) {
                    console.log(resposne);
                    return $q.when(response);
                }, function (error) {
                    console.log("delete error");
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);