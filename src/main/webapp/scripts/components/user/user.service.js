'use strict';

angular.module('watererpApp')
    .factory('User', function ($resource) {
        return $resource('api/users/:login', {}, {
                'query': {method: 'GET', isArray: true},
                'getAll': { method: 'GET', url:'/api/users/getAll', isArray: true},//added by mohib on 20/sep/2016
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                },
                'save': { method:'POST' },
                'update': { method:'PUT' },
                'delete':{ method:'DELETE'}
            });
        });
