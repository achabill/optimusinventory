'use strict';

var optimusInventoryApp = angular
    .module('OptimusInventoryApp', [
        'ngRoute',
        'ngStorage'
    ])
    .config(['$locationProvider', function ($locationProvider) {

        $locationProvider.hashPrefix('');
    }])
    .config(function ($routeProvider) {
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
            .when('/admin', {
                templateUrl: '/views/admin.html',
                controller: 'AdminController',
                controllerAs: 'ctrl'
            })
            .when('/users', {
                templateUrl: '/views/users.html',
                controller: 'UsersController',
                controllerAs: 'ctrl'
            })
            .when('/admin_inventory', {
                templateUrl: '/views/admin_inventory.html',
                controller: 'AdminInventoryController',
                controllerAs: 'ctrl'
            })
            .when('/logs', {
                templateUrl: '/views/logs.html',
                controller: 'LogsController',
                controllerAs: 'ctrl'
            })
            .when('/machineitems', {
                templateUrl: '/views/machineitems.html',
                controller: 'MachineItemsController',
                controllerAs: 'ctrl'
            })
            .when('/machines', {
                templateUrl: '/views/machines.html',
                controller: 'MachineController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
