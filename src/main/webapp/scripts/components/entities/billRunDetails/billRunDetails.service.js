'use strict';

angular.module('watererpApp')
    .factory('BillRunDetails', function ($resource, DateUtils) {
        return $resource('api/billRunDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fromDt = DateUtils.convertDateTimeFromServer(data.fromDt);
                    data.toDt = DateUtils.convertDateTimeFromServer(data.toDt);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
