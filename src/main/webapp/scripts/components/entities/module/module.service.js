'use strict';

angular.module('watererpApp')
    .factory('Module', function ($resource, DateUtils) {
        return $resource('api/modules/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.modifiedDate = DateUtils.convertDateTimeFromServer(data.modifiedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
