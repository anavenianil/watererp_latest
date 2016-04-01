'use strict';

angular.module('watererpApp')
    .factory('AccessList', function ($resource, DateUtils) {
        return $resource('api/accessLists/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
