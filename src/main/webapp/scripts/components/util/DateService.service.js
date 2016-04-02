'use strict';

angular.module('watererpApp')
    .factory('DateService', function ($http) {
        return {
        	getServerDate: function () {
                return $http.get('api/zonedDateTime').then(function (response) {
                    return response.data;
                });
            }
        };
    });
