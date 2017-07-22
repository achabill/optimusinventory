'use strict';

optimusInventoryApp
    .factory('InventoryService', ['$http', "LoginService", function($http, loginService) {
        var baseEndPoint = window.location.origin + "/api/items";
        var token = loginService.token;
        var service = {
            getAllItems: function() {
                return $http.get(baseEndPoint + '/?token=' + token).then(function(response) {
                    return $q.when(response);
                }, function(error) {
                    return $q.reject(error);
                });
            },
            getItemById: function(id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + token).then(function(response) {
                    return $q.when(response);
                }, function(error) {
                    return $q.reject(error);
                });
            },
            postOneItem: function(item) {
                return $http.post(baseEndPoint + '/' + id + '/?token=' + token, item).then(function(response) {
                    return $q.when(response);
                }, function(error) {
                    return $q.reject(error);
                });
            },
            postFile: function(file) {
                return $http.post(baseEndPoint + '/' + id + '/?token=' + token + "&file=" + file).then(function(response) {
                    return $q.when(response);
                }, function(error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);