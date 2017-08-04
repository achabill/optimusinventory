'use strict';

optimusInventoryApp
    .factory('DebtorService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/users";
        var baseEndPoint = "http://localhost:8080/api/debtors";

        var service = {
            token: userService.token,
            getAllDebtors: function () {
                return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getDebtorById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + service.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postDebtor: function (debtor) {
                return $http.post(baseEndPoint + '/?token=' + service.token, debtor).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            updateDebtorById: function (debtor, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + service.token, debtor).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };
        return service;
    }]);