'use strict';

angular.module('watererpApp')
    .factory('Customer', function ($resource, DateUtils) {
        return $resource('api/customers/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestDate = DateUtils.convertDateTimeFromServer(data.requestDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
