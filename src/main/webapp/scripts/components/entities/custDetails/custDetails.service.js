'use strict';

angular.module('watererpApp')
    .factory('CustDetails', function ($resource, DateUtils) {
        return $resource('api/custDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.connDate = DateUtils.convertLocaleDateFromServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateFromServer(data.metReadingDt);
                    data.billDate = DateUtils.convertLocaleDateFromServer(data.billDate);
                    data.ocDate = DateUtils.convertLocaleDateFromServer(data.ocDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    data.ocDate = DateUtils.convertLocaleDateToServer(data.ocDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    data.ocDate = DateUtils.convertLocaleDateToServer(data.ocDate);
                    return angular.toJson(data);
                }
            }
        });
    });
