 'use strict';

angular.module('watererpApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-watererpApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-watererpApp-params')});
                }
                return response;
            }
        };
    });
