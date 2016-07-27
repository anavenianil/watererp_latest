'use strict';

angular.module('watererpApp')
    .factory('MerchantMaster', function ($resource, DateUtils) {
        return $resource('api/merchantMasters/:id', {}, {
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
