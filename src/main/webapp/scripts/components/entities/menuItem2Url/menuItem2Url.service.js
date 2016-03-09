'use strict';

angular.module('watererpApp')
    .factory('MenuItem2Url', function ($resource, DateUtils) {
        return $resource('api/menuItem2Urls/:id', {}, {
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
