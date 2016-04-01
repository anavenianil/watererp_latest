'use strict';

angular.module('watererpApp')
    .factory('BillOfMaterial', function ($resource, DateUtils) {
        return $resource('api/billOfMaterials/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.checkDate = DateUtils.convertLocaleDateFromServer(data.checkDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.checkDate = DateUtils.convertLocaleDateToServer(data.checkDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.checkDate = DateUtils.convertLocaleDateToServer(data.checkDate);
                    return angular.toJson(data);
                }
            }
        });
    });
