'use strict';

angular.module('watererpApp')
    .factory('CollectionTypeMaster', function ($resource, DateUtils) {
        return $resource('api/collectionTypeMasters/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getByTxnType': { method: 'GET', params: {txnType: "txnType" }, url:'/api/collectionTypeMasters/txnType', isArray: true},
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
