'use strict';

angular.module('watererpApp')
    .factory('HydrantComplaint', function ($resource, DateUtils) {
        return $resource('api/hydrantComplaints/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getByComplaintId': { method: 'GET', params: {waterLeakageComplaint: "waterLeakageComplaint" }, url:'/api/hydrantComplaints/getByComplaintId', isArray: false},
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
