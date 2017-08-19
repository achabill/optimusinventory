'use strict';

optimusInventoryApp
    .factory('SalesService', ['$http', '$q', 'UserService', function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/sales";
        var baseEndPoint = "http://localhost:8080/api/sales";

        var service = {
            token: userService.token,
            getAllSales: function () {
                return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                    console.log(response.data);
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getSaleById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postSale: function (sale) {
                return $http.post(baseEndPoint + '/?token=' + service.token, sale).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateSaleById: function (sale, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + service.token, sale).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);