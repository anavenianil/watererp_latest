'use strict';

angular.module('watererpApp')
    .factory('Account', function Account($resource) {
        return $resource('api/account', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    }).factory(
			'AccountService',
			function($http) {
				return {
					getVersion : function(can) {
						return $http.get('api/version').then(function successCallback(response) {
							return response.data;
						}, function errorCallback(response) {
							return "error";
						});
					}
				};
			});
