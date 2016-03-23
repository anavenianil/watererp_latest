'use strict';

angular.module('watererpApp')
    .factory('ApplicationTxnService', function ($http) {
        return {
        	approveRequest: function (id, remarks) {
                return $http.get('api/applicationTxns/approveRequest', {params: {id: id, remarks: remarks}}).then(function (response) {
                    return response.data;
                });
            }/*,
            declineRequest: function (id) {
                return $http.get('api/applicationTxns/declineRequest', {params: {id: id}}).then(function (response) {
                    return response.data;
                });
            }*/
            /*,
            search: function (applicationTxnNo, applicationTxnDt, proposedDt, statusSearch) {
                return $http.get('api/applicationTxns/search', {params: {applicationTxnNo: applicationTxnNo, applicationTxnDt: applicationTxnDt, proposedDt: proposedDt, statusSearch: statusSearch}}).then(function (response) {
                    return response.data;
                });
            },
            
            findByStatus : function(applicationTxnStatus){
            	return $http.get('api/applicationTxn/byStatus', {params: {applicationTxnStatus:applicationTxnStatus}}).then(function(response){
            		return response.data;
            	})
            }*/
        };
    });
