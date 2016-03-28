'use strict';

angular.module('watererpApp')
    .factory('FileUploadMaster', function ($resource, DateUtils) {
        return $resource('api/fileUploadMasters/:id', {}, {
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
