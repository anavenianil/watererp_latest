'use strict';

angular.module('watererpApp')
    .factory('ApplicationTypeMaster', function ($resource, DateUtils) {
        return $resource('api/applicationTypeMasters/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    data.updatedDate = DateUtils.convertDateTimeFromServer(data.updatedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
