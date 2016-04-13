'use strict';

angular.module('watererpApp')
    .factory('Receipt', function ($resource, DateUtils) {
        return $resource('api/receipts/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.checkOrDdDate = DateUtils.convertLocaleDateFromServer(data.checkOrDdDate);
                    data.receiptDate = DateUtils.convertLocaleDateFromServer(data.receiptDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.checkOrDdDate = DateUtils.convertLocaleDateToServer(data.checkOrDdDate);
                    data.receiptDate = DateUtils.convertLocaleDateToServer(data.receiptDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.checkOrDdDate = DateUtils.convertLocaleDateToServer(data.checkOrDdDate);
                    data.receiptDate = DateUtils.convertLocaleDateToServer(data.receiptDate);
                    return angular.toJson(data);
                }
            }
        });
    });
