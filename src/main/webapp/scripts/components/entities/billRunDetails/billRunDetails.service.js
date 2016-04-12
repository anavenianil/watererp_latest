'use strict';

angular.module('watererpApp')
    .factory('BillRunDetails', function ($resource, DateUtils) {
        return $resource('api/billRunDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fromDt = DateUtils.convertLocaleDateFromServer(data.fromDt);
                    data.toDt = DateUtils.convertLocaleDateFromServer(data.toDt);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.fromDt = DateUtils.convertLocaleDateToServer(data.fromDt);
                    data.toDt = DateUtils.convertLocaleDateToServer(data.toDt);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.fromDt = DateUtils.convertLocaleDateToServer(data.fromDt);
                    data.toDt = DateUtils.convertLocaleDateToServer(data.toDt);
                    return angular.toJson(data);
                }
            }
        });
    });
