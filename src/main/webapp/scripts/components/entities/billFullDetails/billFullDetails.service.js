'use strict';

angular.module('watererpApp')
    .factory('BillFullDetails', function ($resource, DateUtils) {
        return $resource('api/billFullDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.connDate = DateUtils.convertLocaleDateFromServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateFromServer(data.metReadingDt);
                    data.lastPymtDt = DateUtils.convertLocaleDateFromServer(data.lastPymtDt);
                    data.billDate = DateUtils.convertLocaleDateFromServer(data.billDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.lastPymtDt = DateUtils.convertLocaleDateToServer(data.lastPymtDt);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.lastPymtDt = DateUtils.convertLocaleDateToServer(data.lastPymtDt);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    return angular.toJson(data);
                }
            }
        });
    });
