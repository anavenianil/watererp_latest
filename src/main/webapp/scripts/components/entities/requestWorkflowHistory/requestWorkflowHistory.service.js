'use strict';

angular.module('watererpApp')
    .factory('RequestWorkflowHistory', function ($resource, DateUtils) {
        return $resource('api/requestWorkflowHistorys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.assignedDate = DateUtils.convertDateTimeFromServer(data.assignedDate);
                    data.actionedDate = DateUtils.convertDateTimeFromServer(data.actionedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
