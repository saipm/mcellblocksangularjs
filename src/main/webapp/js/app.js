'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', ['ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/dummy', {templateUrl: 'partials/dummy.html', controller: 'DummyCtrl'});
        $routeProvider.when('/dummynew', {templateUrl: 'partials/dummynew.html', controller: 'DummyCtrl'});
        $routeProvider.when('/user-list', {templateUrl: 'partials/user-list.html', controller: 'UserListCtrl'});
        $routeProvider.when('/user-detail/:id', {templateUrl: 'partials/user-detail.html', controller: 'UserDetailCtrl'});
        $routeProvider.when('/user-creation', {templateUrl: 'partials/user-creation.html', controller: 'UserCreationCtrl'});
        $routeProvider.when('/device-list', {templateUrl: 'partials/device-list.html', controller: 'DeviceListCtrl'});
        $routeProvider.when('/testcase-list', {templateUrl: 'partials/testcase-list.html', controller: 'TestcaseListCtrl'});
        $routeProvider.when('/device-info', {templateUrl: 'partials/device-info.html', controller: 'DeviceListCtrl'});
        $routeProvider.otherwise({redirectTo: '/device-list'});
    }]);
