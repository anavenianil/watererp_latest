'use strict';

angular.module('watererpApp')
    .factory('ApprovalDetails', function ($resource, DateUtils) {
        return $resource('api/approvalDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.approvedDate = DateUtils.convertDateTimeFromServer(data.approvedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
