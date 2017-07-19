'use strict';

/**
 * @ngdoc overview
 * @name OptimusInventoryApp
 * @description
 * # Client app
 *
 * Main module of the application.
 */
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
                templateUrl: '/views/login.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    });