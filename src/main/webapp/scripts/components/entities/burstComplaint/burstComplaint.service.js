'use strict';

angular.module('watererpApp')
    .factory('BurstComplaint', function ($resource, DateUtils) {
        return $resource('api/burstComplaints/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getByComplaintId': { method: 'GET', params: {waterLeakageComplaint: "waterLeakageComplaint" }, url:'/api/burstComplaints/getByComplaintId', isArray: false},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
