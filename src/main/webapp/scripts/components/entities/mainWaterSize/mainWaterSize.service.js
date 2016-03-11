'use strict';

angular.module('watererpApp')
    .factory('MainWaterSize', function ($resource, DateUtils) {
        return $resource('api/mainWaterSizes/:id', {}, {
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
