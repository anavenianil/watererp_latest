'use strict';

angular.module('watererpApp')
    .factory('SewerageChargeMaster', function ($resource, DateUtils) {
        return $resource('api/sewerageChargeMasters/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getChargeByCategoryId': { method: 'GET', params: {categoryId: "categoryId" }, url:'/api/sewerageChargeMasters/getChargeByCategoryId', isArray: false},
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
