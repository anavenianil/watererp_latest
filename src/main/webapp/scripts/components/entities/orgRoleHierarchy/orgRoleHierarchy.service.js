'use strict';

angular.module('watererpApp')
    .factory('OrgRoleHierarchy', function ($resource, DateUtils) {
        return $resource('api/orgRoleHierarchys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getAll': { method: 'GET', url:'/api/orgRoleHierarchys/getAll', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    data.lastModifiedDate = DateUtils.convertDateTimeFromServer(data.lastModifiedDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
