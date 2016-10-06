'use strict';

angular.module('watererpApp')
    .factory('EmpRoleMapping', function ($resource, DateUtils) {
        return $resource('api/empRoleMappings/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'getByUserId': { method: 'GET', params: {userId: "userId" }, url:'/api/empRoleMappings/getByUserId', isArray: false},
            'getMappingsByUserId': { method: 'GET', params: {userId: "userId" }, url:'/api/empRoleMappings/getMappingsByUserId', isArray: true},
            'getMappingsByLogin': { method: 'GET', params:{domainObjectId: "domainObjectId", requestTypeId: "requestTypeId"}, url:'/api/empRoleMappings/getMappingsByLogin', isArray: false},
            'getMappingsByOrgRoleInstance': { method: 'GET', params:{orgRoleInstanceId: "orgRoleInstanceId"}, url:'/api/empRoleMappings/getMappingsByOrgRoleInstance', isArray: false},
            
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
