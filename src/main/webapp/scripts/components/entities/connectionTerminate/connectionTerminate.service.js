'use strict';

angular.module('watererpApp')
    .factory('ConnectionTerminate', function ($resource, DateUtils) {
        return $resource('api/connectionTerminates/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestDate = DateUtils.convertLocaleDateFromServer(data.requestDate);
                    data.meterRecoveredDate = DateUtils.convertLocaleDateFromServer(data.meterRecoveredDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.requestDate = DateUtils.convertLocaleDateToServer(data.requestDate);
                    data.meterRecoveredDate = DateUtils.convertLocaleDateToServer(data.meterRecoveredDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.requestDate = DateUtils.convertLocaleDateToServer(data.requestDate);
                    data.meterRecoveredDate = DateUtils.convertLocaleDateToServer(data.meterRecoveredDate);
                    return angular.toJson(data);
                }
            }
        });
    });
