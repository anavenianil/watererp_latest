'use strict';

angular.module('waterERPApp')
    .factory('BillDetails', function ($resource, DateUtils) {
        return $resource('api/billDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.bill_date = DateUtils.convertLocaleDateFromServer(data.bill_date);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.bill_date = DateUtils.convertLocaleDateToServer(data.bill_date);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.bill_date = DateUtils.convertLocaleDateToServer(data.bill_date);
                    return angular.toJson(data);
                }
            }
        });
    });
