'use strict';

angular.module('watererpApp')
    .factory('SibEntry', function ($resource, DateUtils) {
        return $resource('api/sibEntrys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.soDate = DateUtils.convertDateTimeFromServer(data.soDate);
                    data.demandDate = DateUtils.convertDateTimeFromServer(data.demandDate);
                    data.sibDate = DateUtils.convertDateTimeFromServer(data.sibDate);
                    data.irDate = DateUtils.convertDateTimeFromServer(data.irDate);
                    data.toUser = DateUtils.convertDateTimeFromServer(data.toUser);
                    data.fromUser = DateUtils.convertDateTimeFromServer(data.fromUser);
                    data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    data.lastModifiedDate = DateUtils.convertDateTimeFromServer(data.lastModifiedDate);
                    data.dcDate = DateUtils.convertDateTimeFromServer(data.dcDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
