'use strict';

angular
    .module('OptimusInventoryApp', [
        'ngCookies',
        'ngRoute'
    ])
    .config(['$locationProvider', function($locationProvider){
        $locationProvider.hashPrefix('');
    }])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/views/login.html',
                controller: 'LoginController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });