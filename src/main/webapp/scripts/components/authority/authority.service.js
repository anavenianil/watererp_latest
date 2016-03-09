'use strict';

angular.module('watererpApp')
    .factory('Authority', function ($resource) {
        return $resource('api/authoritys/:name', {}, {
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
