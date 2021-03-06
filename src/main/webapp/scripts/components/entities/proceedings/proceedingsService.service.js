'use strict';

angular.module('watererpApp')
    .factory('ProceedingsService', function ($resource, DateUtils) {
        return $resource('api/proceedingss/custom/:applicationTxnId', {}, {
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
