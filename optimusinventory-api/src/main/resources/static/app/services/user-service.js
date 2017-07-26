'use strict';

optimusInventoryApp.factory('UserService', ['$http', '$q', function ($http, $q) {
    console.log("user service");
    //var baseEndPoint = window.location.origin + "/api/users";
    var baseEndPoint = "http://localhost:8080/api/users";
    var service = {

        isLoggedin: false,
        token: null,
        user: null,
        error: null,
        login: function (user) {
            return $http.post(baseEndPoint + '/login', user).then(function (response) {
                service.isLoggedin = true;
                service.token = response.data.token;
                service.user = response.data.user;
                return $q.when(response);

            }, function (error) {
                service.error = error.data.message;
                return $q.reject(error);
            });
        },
        signup: function (user) {
            return $http.post(baseEndPoint + '/signup?token=' + service.token, user).then(function (response) {
                service.isLoggedin = true;
                service.token = response.data.token;
                service.user = response.data.user;
                return $q.when(response);
            }, function (error) {
                service.error = error.data.message;
                return $q.reject(error);
            });
        },
        logout: function () {
            return $http.get(baseEndPoint + '/logout?token=' + service.token).then(function (response) {
                return $q.when(response);
            },
                function (error) {
                    service.error = error.data.message;
                    console.log(error.data);
                    return $q.reject(error);
                });
        },
        getAllUsers: function () {
            console.log("get all users");
            return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                return $q.when(response);
            }, function (error) {
                service.error = error.data.message;
                console.log(error.data);
                return $q.reject(error);
            });
        }
    };
    return service;
}]);