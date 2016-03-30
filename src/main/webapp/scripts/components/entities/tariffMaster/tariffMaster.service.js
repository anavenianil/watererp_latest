'use strict';

angular.module('waterERPApp')
    .factory('TariffMaster', function ($resource, DateUtils) {
        return $resource('api/tariffMasters/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.validFrom = DateUtils.convertDateTimeFromServer(data.validFrom);
                    data.validTo = DateUtils.convertDateTimeFromServer(data.validTo);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
