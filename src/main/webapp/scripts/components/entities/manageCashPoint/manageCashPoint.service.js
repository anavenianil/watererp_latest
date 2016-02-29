'use strict';

angular.module('watererpApp')
    .factory('ManageCashPoint', function ($resource, DateUtils) {
        return $resource('api/manageCashPoints/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.todayDate = DateUtils.convertDateTimeFromServer(data.todayDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
