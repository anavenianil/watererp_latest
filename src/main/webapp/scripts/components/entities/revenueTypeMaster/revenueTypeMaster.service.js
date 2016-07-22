'use strict';

angular.module('watererpApp')
    .factory('RevenueTypeMaster', function ($resource, DateUtils) {
        return $resource('api/revenueTypeMasters/:id', {}, {
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
