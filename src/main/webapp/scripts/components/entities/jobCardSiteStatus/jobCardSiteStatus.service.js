'use strict';

angular.module('watererpApp')
    .factory('JobCardSiteStatus', function ($resource, DateUtils) {
        return $resource('api/jobCardSiteStatuss/:id', {}, {
            'query': { method: 'GET', isArray: true},
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
