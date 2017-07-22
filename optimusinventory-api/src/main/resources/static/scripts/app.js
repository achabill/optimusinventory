'use strict';

var optimusInventoryApp = angular
    .module('OptimusInventoryApp', [
        'ngCookies',
        'ngRoute'
    ])
    .config(['$locationProvider', function($locationProvider) {
        $locationProvider.hashPrefix('');
    }])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/views/login.html',
                controller: 'LoginController',
                controllerAs: 'ctrl'
            })
            .when('/inventory', {
                templateUrl: '/views/inventory.html',
                controller: 'InventoryController',
                controllerAs: 'ctrl'
            })
            .when('/sales', {
                templateUrl: '/views/sales.html',
                controller: 'SalesController',
                controllerAs: 'ctrl'
            })
            .when('/debtors', {
                templateUrl: '/views/debtors.html',
                controller: 'DebtorsController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });