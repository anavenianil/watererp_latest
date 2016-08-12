'use strict';

angular.module('watererpApp')
    .factory('HydrantComplaint', function ($resource, DateUtils) {
        return $resource('api/hydrantComplaints/:id', {}, {
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
