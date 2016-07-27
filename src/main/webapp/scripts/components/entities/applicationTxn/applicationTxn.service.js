'use strict';

angular.module('watererpApp')
    .factory('ApplicationTxn', function ($resource, DateUtils) {
        return $resource('api/applicationTxns/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.requestedDate = DateUtils.convertLocaleDateFromServer(data.requestedDate);
                    data.connectionDate = DateUtils.convertLocaleDateFromServer(data.connectionDate);
                    data.approvedDate = DateUtils.convertLocaleDateFromServer(data.approvedDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.connectionDate = DateUtils.convertLocaleDateToServer(data.connectionDate);
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.requestedDate = DateUtils.convertLocaleDateToServer(data.requestedDate);
                    data.connectionDate = DateUtils.convertLocaleDateToServer(data.connectionDate);
                    data.approvedDate = DateUtils.convertLocaleDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            }
        });
    })
    .factory('ApplicationTxnSearchCAN', function ($resource) {
    	return $resource('api/applicationTxns/searchCan/:can', {}, {
    		'query': { method: 'GET', isArray: true}
        });
    });
