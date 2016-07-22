'use strict';

angular.module('watererpApp')
    .factory('Customer', function ($resource, DateUtils) {
        return $resource('api/customers/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestedDate = DateUtils.convertLocaleDateFromServer(data.requestedDate);
                    data.approvedDate = DateUtils.convertLocaleDateFromServer(data.approvedDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            }
        });
    });
