'use strict';

angular.module('watererpApp')
    .factory('PercentageMaster', function ($resource, DateUtils) {
        return $resource('api/percentageMasters/:id', {}, {
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
