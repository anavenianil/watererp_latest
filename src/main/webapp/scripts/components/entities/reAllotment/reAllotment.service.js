'use strict';

angular.module('watererpApp')
    .factory('ReAllotment', function ($resource, DateUtils) {
        return $resource('api/reAllotments/:id', {}, {
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
