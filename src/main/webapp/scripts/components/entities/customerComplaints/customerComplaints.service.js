'use strict';

angular.module('watererpApp')
    .factory('CustomerComplaints', function ($resource, DateUtils) {
        return $resource('api/customerComplaintss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.complaintDate = DateUtils.convertLocaleDateFromServer(data.complaintDate);
                    return data;
                }
            },
            'getByCan' : {method: 'GET', url:'api/customerComplaints/getByCan/:can/:status', isArray: true}, 
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.complaintDate = DateUtils.convertLocaleDateToServer(data.complaintDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.complaintDate = DateUtils.convertLocaleDateToServer(data.complaintDate);
                    return angular.toJson(data);
                }
            }
        });
    });