'use strict';

angular.module('watererpApp')
    .factory('PaymentTypes', function ($resource, DateUtils) {
        return $resource('api/paymentTypess/:id', {}, {
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
