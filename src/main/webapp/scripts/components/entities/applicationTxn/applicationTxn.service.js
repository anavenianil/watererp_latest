'use strict';

angular.module('watererpApp')
    .factory('ApplicationTxn', function ($resource, DateUtils) {
        return $resource('api/applicationTxns/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestedDate = DateUtils.convertDateTimeFromServer(data.requestedDate);
                    data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    data.updatedDate = DateUtils.convertDateTimeFromServer(data.updatedDate);
                    data.connectionDate = DateUtils.convertLocaleDateFromServer(data.connectionDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.connectionDate = DateUtils.convertLocaleDateToServer(data.connectionDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.connectionDate = DateUtils.convertLocaleDateToServer(data.connectionDate);
                    return angular.toJson(data);
                }
            }
        });
    });
