'use strict';

angular.module('watererpApp')
    .factory('OrgRoleInstance', function ($resource, DateUtils) {
        return $resource('api/orgRoleInstances/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    data.lastModifiedDate = DateUtils.convertDateTimeFromServer(data.lastModifiedDate);
                    data.isHead = DateUtils.convertDateTimeFromServer(data.isHead);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
