'use strict';

angular.module('watererpApp')
    .factory('Url2Role', function ($resource, DateUtils) {
        return $resource('api/url2Roles/:id', {}, {
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
