'use strict';

angular.module('watererpApp')
    .factory('ExpenseDetails', function ($resource, DateUtils) {
        return $resource('api/expenseDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.expenseDt = DateUtils.convertDateTimeFromServer(data.expenseDt);
                    data.instrDt = DateUtils.convertLocaleDateFromServer(data.instrDt);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.instrDt = DateUtils.convertLocaleDateToServer(data.instrDt);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.instrDt = DateUtils.convertLocaleDateToServer(data.instrDt);
                    return angular.toJson(data);
                }
            }
        });
    });
