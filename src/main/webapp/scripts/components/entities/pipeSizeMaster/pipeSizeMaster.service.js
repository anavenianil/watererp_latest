'use strict';

angular.module('watererpApp')
    .factory('PipeSizeMaster', function ($resource, DateUtils) {
        return $resource('api/pipeSizeMasters/:id', {}, {
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
    })
    .factory('GetPipeSizeDetail',
				function($http) {
					return {
						findByPipeSize : function(pipeSize) {
							return $http.get('api/pipeSizeMasters/forPipeSize', {
								params : {
									pipeSize : pipeSize,
								}
							}).then(function successCallback(response) {
								return response.data;
							}, function errorCallback(response) {
								return "error";
							});
						}
					};
				});
