'use strict';

angular.module('watererpApp')
    .factory('BillOfMaterial', function ($resource, DateUtils) {
        return $resource('api/billOfMaterials/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.checkOrDdDate = DateUtils.convertLocaleDateFromServer(data.checkOrDdDate);
                    data.billDate = DateUtils.convertLocaleDateFromServer(data.billDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.checkOrDdDate = DateUtils.convertLocaleDateToServer(data.checkOrDdDate);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.checkOrDdDate = DateUtils.convertLocaleDateToServer(data.checkOrDdDate);
                    data.billDate = DateUtils.convertLocaleDateToServer(data.billDate);
                    return angular.toJson(data);
                }
            }
        });
    });
