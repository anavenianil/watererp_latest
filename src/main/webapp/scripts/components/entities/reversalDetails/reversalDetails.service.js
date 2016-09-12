'use strict';

angular.module('watererpApp')
    .factory('ReversalDetails', function ($resource, DateUtils) {
        return $resource('api/reversalDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.cancelledDate = DateUtils.convertLocaleDateFromServer(data.cancelledDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.cancelledDate = DateUtils.convertLocaleDateToServer(data.cancelledDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.cancelledDate = DateUtils.convertLocaleDateToServer(data.cancelledDate);
                    return angular.toJson(data);
                }
            }
        });
    });
