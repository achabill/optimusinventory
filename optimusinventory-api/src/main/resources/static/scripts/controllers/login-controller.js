'use strict';

angular.module('OptimusInventoryApp')
    .controller('LoginController',['LoginService',function(loginService){
        var self = this;
        self.getBaseEndPoint = function(){
            console.log(loginService.getBaseEndPoint());
        };
    }]);