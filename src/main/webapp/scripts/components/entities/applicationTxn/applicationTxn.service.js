'use strict';

angular.module('watererpApp')
    .factory('ApplicationTxn', function ($resource, DateUtils) {
        return $resource('api/applicationTxns/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestedDate = DateUtils.convertDateTimeFromServer(data.requestedDate);
                    data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    data.updatedDate = DateUtils.convertDateTimeFromServer(data.updatedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
