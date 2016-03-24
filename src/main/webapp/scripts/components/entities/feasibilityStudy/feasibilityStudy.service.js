'use strict';

angular.module('watererpApp')
    .factory('FeasibilityStudy', function ($resource, DateUtils) {
        return $resource('api/feasibilityStudys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    data.modifiedDate = DateUtils.convertDateTimeFromServer(data.modifiedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
