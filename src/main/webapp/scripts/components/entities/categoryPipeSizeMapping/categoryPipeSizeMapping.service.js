'use strict';

angular.module('watererpApp')
    .factory('CategoryPipeSizeMapping', function ($resource, DateUtils) {
        return $resource('api/categoryPipeSizeMappings/:id', {}, {
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
