'use strict';

angular.module('watererpApp')
    .factory('JobCardItemRequirement', function ($resource, DateUtils) {
        return $resource('api/jobCardItemRequirements/:id', {}, {
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
