'use strict';

angular.module('watererpApp')
    .factory('WorkflowRelationships', function ($resource, DateUtils) {
        return $resource('api/workflowRelationshipss/:id', {}, {
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
