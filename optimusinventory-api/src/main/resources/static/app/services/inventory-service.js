'use strict';

optimusInventoryApp
    .factory('InventoryService', ['$http', '$q', "UserService", function ($http, $q, userService) {
        //var baseEndPoint = window.location.origin + "/api/items";
        var baseEndPoint = "http://localhost:8080/api/items";

        var service = {
            token: userService.token,
            getAllItems: function () {
                return $http.get(baseEndPoint + '/?token=' + userService.token).then(function (response) {
                    //console.log(response.data);
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            getItemById: function (id) {
                return $http.get(baseEndPoint + '/' + id + '/?token=' + userService.token).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postOneItem: function (item) {
                //console.log("posting item..");
                //console.log(item);
                return $http.post(baseEndPoint + '/?token=' + userService.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            },
            postFile: function (file) {

                var fd = new FormData();
                fd.append('file', file);
                fd.append('token', userService.token);
                var defer = $q.defer();

                $http({
                    method: 'POST',
                    data: fd,
                    url: baseEndPoint + '/file/',
                    headers: { 'Content-Type': undefined },
                    uploadEventHandlers: {
                        progress: function (e) {
                            defer.notify(e.loaded * 100 / e.total);
                        }
                    }
                }).then(defer.resolve.bind(defer), defer.reject.bind(defer));
                return defer.promise;
                /* })
                 //console.log(fd);
                 return $http.post(baseEndPoint + "/file/", fd, {
                     transformRequest: angular.identity,
                     headers: {
                         'Content-Type': undefined
                     }
                 }).then(function (response) {
                     return $q.when(response);
                 }, function (error) {
                     return $q.reject(error);
                 });
                 */
            },
            updateItemById: function (item, id) {
                return $http.put(baseEndPoint + '/' + id + '/?token=' + userService.token, item).then(function (response) {
                    return $q.when(response);
                }, function (error) {
                    return $q.reject(error);
                });
            }
        };

        return service;
    }]);