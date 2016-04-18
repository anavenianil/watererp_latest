'use strict';

angular.module('watererpApp')
    .factory('Proceedings', function ($resource, DateUtils) {
        return $resource('api/proceedingss/:id', {}, {
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
    })
    .factory('GetProceedings', function ($resource, DateUtils) {
    	return $resource('api/proceedingss/forAppTxn/:applicationTxnId', {}, {
    		'query': { method: 'GET', isArray: true}
        });
    });
