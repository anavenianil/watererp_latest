'use strict';

angular.module('watererpApp')
    .factory('MainSewerageSize', function ($resource, DateUtils) {
        return $resource('api/mainSewerageSizes/:id', {}, {
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
