'use strict';

angular.module('watererpApp')
    .factory('MeterChange', function ($resource, DateUtils) {
        return $resource('api/meterChanges/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.approvedDate = DateUtils.convertLocaleDateFromServer(data.approvedDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            }
        });
    });
