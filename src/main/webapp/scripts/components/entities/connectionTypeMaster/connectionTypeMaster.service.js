'use strict';

angular.module('watererpApp')
    .factory('ConnectionTypeMaster', function ($resource, DateUtils) {
        return $resource('api/connectionTypeMasters/:id', {}, {
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
