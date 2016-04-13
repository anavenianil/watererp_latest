'use strict';

angular.module('watererpApp')
    .factory('MeterStatus', function ($resource, DateUtils) {
        return $resource('api/meterStatuss/:id', {}, {
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
