'use strict';

angular.module('OptimusInventoryApp')
.factory('LoginService',['$http', function($http){
    var service = {
        getBaseEndPoint: function(){
            return window.location;
        }
    };
    return service;
}]);