'use strict';

angular.module('watererpApp')
    .factory('ItemDetails', function ($resource, DateUtils) {
        return $resource('api/itemDetailss/:id', {}, {
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
