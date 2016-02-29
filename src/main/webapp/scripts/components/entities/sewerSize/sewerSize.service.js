'use strict';

angular.module('watererpApp')
    .factory('SewerSize', function ($resource, DateUtils) {
        return $resource('api/sewerSizes/:id', {}, {
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
