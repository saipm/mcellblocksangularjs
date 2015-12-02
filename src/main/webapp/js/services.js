'use strict';

/* Services */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

var services = angular.module('ngdemo.services', ['ngResource']);

services.factory('DummyFactory', function ($resource) {
    return $resource('/ngdemo/web/dummy', {}, {
        query: { method: 'GET', params: {}, isArray: false }
    })
});

services.factory('UsersFactory', function ($resource) {
    return $resource('/ngdemo/web/users', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('UserFactory', function ($resource) {
    return $resource('/ngdemo/web/users/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
services.factory('DevicesFactory', function ($resource) {
    return $resource('/ngdemo/web/devices', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});
services.factory('TestDeviceFactory', function ($resource) {
    return $resource('/ngdemo/web/devicetests', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});
services.factory('TestcasesFactory', function ($resource) {
    return $resource('/ngdemo/web/testcases', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});
services.factory('DeviceFactory', function ($resource) {
    return $resource('/ngdemo/web/devices/:device', {}, {
        show: { method: 'GET',params: {device: '@device'}},
        update: { method: 'PUT', params: {device: '@device'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
services.factory('TestcaseFactory', function ($resource) {
    return $resource('/ngdemo/web/testcases/:testcase', {}, {
        show: { method: 'GET',params: {testcase: '@testcase'}},
        update: { method: 'PUT', params: {testcase: '@testcase'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});