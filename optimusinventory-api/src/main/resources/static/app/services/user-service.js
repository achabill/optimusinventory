'use strict';

optimusInventoryApp.factory('UserService', ['$http', '$q', '$localStorage', function ($http, $q, $localStorage) {
    //var baseEndPoint = window.location.origin + "/api/users";
    var baseEndPoint = "http://localhost:8080/api/users";
    //console.log('user service');
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

                $localStorage.$default({
                    user: service.user,
                    token: service.token
                });

                return $q.when(response);

            }, function (error) {
                service.error = error.data.message;
                return $q.reject(error);
            });
        },
        addUser: function (user) {
            return $http.post(baseEndPoint + '/?token=' + service.token, user).then(function (response) {
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
                    //console.log(error.data);
                    return $q.reject(error);
                });
        },
        getAllUsers: function () {
            return $http.get(baseEndPoint + '/?token=' + service.token).then(function (response) {
                return $q.when(response);
            }, function (error) {
                service.error = error.data.message;
                //console.log(error.data);
                return $q.reject(error);
            });
        },

        getAllPrivileges: function () {
            return $http.get(baseEndPoint + '/privileges?token=' + service.token).then(function (response) {
                return $q.when(response);
            }, function (error) {
                service.error = error.data.message;
                //console.log(error.data);
                return $q.reject(error);
            });
        }
    };

    service.user = $localStorage.user;
    service.token = $localStorage.token;
    if (service.user == undefined) {
        service.user = {
            "username": ""
        };
    }
    if (service.token == undefined) {
        service.token = "";
    }
    return service;
}]);