'use strict';

angular.module('watererpApp')
    .factory('WaterLeakageComplaint', function ($resource, DateUtils) {
        return $resource('api/waterLeakageComplaints/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.complaintDateTime = DateUtils.convertDateTimeFromServer(data.complaintDateTime);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
