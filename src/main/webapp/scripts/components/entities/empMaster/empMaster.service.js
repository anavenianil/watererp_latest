'use strict';

angular.module('watererpApp')
    .factory('EmpMaster', function ($resource, DateUtils) {
        return $resource('api/empMasters/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dateOfBirth = DateUtils.convertLocaleDateFromServer(data.dateOfBirth);
                    data.joiningDate = DateUtils.convertLocaleDateFromServer(data.joiningDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dateOfBirth = DateUtils.convertLocaleDateToServer(data.dateOfBirth);
                    data.joiningDate = DateUtils.convertLocaleDateToServer(data.joiningDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dateOfBirth = DateUtils.convertLocaleDateToServer(data.dateOfBirth);
                    data.joiningDate = DateUtils.convertLocaleDateToServer(data.joiningDate);
                    return angular.toJson(data);
                }
            }
        });
    });
