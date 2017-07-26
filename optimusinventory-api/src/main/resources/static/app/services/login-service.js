'use strict';

optimusInventoryApp.factory('LoginService', ['$http', '$q', function($http, $q) {
    var baseEndPoint = window.location.origin + "/api/users";
    var service = {
        isLoggedin: false,
        token: null,
        user: null,
        error: null,
        login: function(user) {
            return $http.post(baseEndPoint + '/login', user).then(function(response) {
                service.isLoggedin = true;
                console.log(response);
                service.token = response.data.token;
                service.user = response.data.user;
            }, function(error) {
                service.error = error.data.message;
                return $q.reject(error);
            });
        },
        signup: function(user) {
            return $http.post(baseEndPoint + '/signup?token=' + service.token, user).then(function(response) {
                service.isLoggedin = true;
                service.token = response.data.token;
                service.user = response.data.user;
            }, function(error) {
                service.error = error.data.message;
                return $q.reject(error);
            });
        },
        logout: function() {
            return $http.get(baseEndPoint + '/logout?token=' + service.token).then(function() {},
                function(error) {
                    service.error = error.data.message;
                    console.log(error.data);
                    return $q.reject(error);
                });
        },
        getAllUsers: function() {
            return $http.get(baseEndPoint + '/?token=' + service.token).then(function() {},
                function(error) {
                    service.error = error.data.message;
                    console.log(error.data);
                    return $q.reject(error);
                });
        }
    };
    return service;
}]);