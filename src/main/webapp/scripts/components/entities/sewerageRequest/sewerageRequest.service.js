'use strict';

angular.module('watererpApp')
    .factory('SewerageRequest', function ($resource, DateUtils) {
        return $resource('api/sewerageRequests/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'sewerageRequestApproval': { method: 'POST', url:'/api/sewerageRequests/sewerageRequestApproval', isArray: false},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestedDate = DateUtils.convertLocaleDateFromServer(data.requestedDate);
                    data.paymentDate = DateUtils.convertLocaleDateFromServer(data.paymentDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.paymentDate = DateUtils.convertLocaleDateToServer(data.paymentDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.paymentDate = DateUtils.convertLocaleDateToServer(data.paymentDate);
                    return angular.toJson(data);
                }
            }
        });
    });
