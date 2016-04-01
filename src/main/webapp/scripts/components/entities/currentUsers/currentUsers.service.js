'use strict';

angular.module('watererpApp')
    .factory('CurrentUsers', function ($resource, DateUtils) {
        return $resource('api/currentUserss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.loginTime = DateUtils.convertDateTimeFromServer(data.loginTime);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
