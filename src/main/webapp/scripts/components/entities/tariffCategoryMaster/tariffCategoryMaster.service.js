'use strict';

angular.module('watererpApp')
    .factory('TariffCategoryMaster', function ($resource, DateUtils) {
        return $resource('api/tariffCategoryMasters/:id', {}, {
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
