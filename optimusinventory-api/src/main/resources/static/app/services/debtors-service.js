'use strict';

optimusInventoryApp
    .factory('DebtorService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/debtors";
        var baseEndPoint = "http://localhost:8080/api/debtors";

        var service = {
            getAllDebtors: function () {
                return $http.get(baseEndPoint + '/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getDebtorById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postDebtor: function (debtor) {
                return $http.post(baseEndPoint + '/?token=' + userService.token, debtor).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateDebtorById: function (debtor, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + userService.token, debtor).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);