'use strict';

angular.module('watererpApp')
    .factory('Adjustments', function ($resource, DateUtils) {
        return $resource('api/adjustmentss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.txnTime = DateUtils.convertDateTimeFromServer(data.txnTime);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
