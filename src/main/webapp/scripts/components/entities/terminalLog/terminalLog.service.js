'use strict';

angular.module('watererpApp')
    .factory('TerminalLog', function ($resource, DateUtils) {
        return $resource('api/terminalLogs/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.lastModified = DateUtils.convertDateTimeFromServer(data.lastModified);
                    data.bankDepositDate = DateUtils.convertLocaleDateFromServer(data.bankDepositDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.bankDepositDate = DateUtils.convertLocaleDateToServer(data.bankDepositDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.bankDepositDate = DateUtils.convertLocaleDateToServer(data.bankDepositDate);
                    return angular.toJson(data);
                }
            }
        });
    });
