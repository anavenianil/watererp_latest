'use strict';

angular.module('watererpApp')
    .factory('FeasibilityStudy', function ($resource, DateUtils) {
        return $resource('api/feasibilityStudys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    data.modifiedDate = DateUtils.convertDateTimeFromServer(data.modifiedDate);
                    data.preparedDate = DateUtils.convertDateTimeFromServer(data.preparedDate);
                    data.zonalHeadApprovalDate = DateUtils.convertDateTimeFromServer(data.zonalHeadApprovalDate);
                    data.deptHeadInspectedDate = DateUtils.convertDateTimeFromServer(data.deptHeadInspectedDate);
                    data.operationMangrapproveDate = DateUtils.convertDateTimeFromServer(data.operationMangrapproveDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    })
    .factory('GetFeasibilityStudy', function ($resource, DateUtils) {
    	return $resource('api/feasibilityStudyss/custom/:applicationTxnId', {}, {
    		'query': { method: 'GET', isArray: true}
        });
    });
