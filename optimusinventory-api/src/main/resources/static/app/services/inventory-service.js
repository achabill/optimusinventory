'use strict';

optimusInventoryApp
    .factory('InventoryService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/users";
        var baseEndPoint = "http://localhost:8080/api/items";

        var service = {
            token: userService.token,
            getAllItems: function () {
                console.log('get all items');
                return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getItemById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postOneItem: function (item) {
                return $http.post(baseEndPoint + '/' + id + '/?token=' + token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postFile: function (file) {
                return $http.post(baseEndPoint + '/' + id + '/?token=' + token + "&file=" + file).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);