'use strict';

angular.module('watererpApp')
    .factory('MeterDetails', function ($resource, DateUtils) {
        return $resource('api/meterDetailss/:id', {}, {
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
