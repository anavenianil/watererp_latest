'use strict';

angular.module('watererpApp')
    .factory('ValveComplaint', function ($resource, DateUtils) {
        return $resource('api/valveComplaints/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getByComplaintId': { method: 'GET', params: {waterLeakageComplaint: "waterLeakageComplaint" }, url:'/api/valveComplaints/getByComplaintId', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.closedTime = DateUtils.convertDateTimeFromServer(data.closedTime);
                    data.openTime = DateUtils.convertDateTimeFromServer(data.openTime);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
