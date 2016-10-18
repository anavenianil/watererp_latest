'use strict';

angular.module('watererpApp')
    .factory('Proceedings', function ($resource, DateUtils) {
        return $resource('api/proceedingss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'saveFeasibilityReport': { method: 'POST', url:'/api/proceedingss/saveFeasibilityReport', isArray: false},
            'getByApplicationTxn': { method: 'GET', params: {applicationTxnId: "applicationTxnId" }, url:'/api/proceedingss/getByApplicationTxn', isArray: false},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    })
    .factory('GetProceedings', function ($resource, DateUtils) {
    	return $resource('api/proceedingss/forAppTxn/:applicationTxnId', {}, {
    		'query': { method: 'GET', isArray: true}
        });
    });

