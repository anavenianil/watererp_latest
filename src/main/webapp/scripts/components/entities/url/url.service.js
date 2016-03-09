'use strict';

angular.module('watererpApp')
    .factory('Url', function ($resource, DateUtils) {
        return $resource('api/urls/:id', {}, {
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
