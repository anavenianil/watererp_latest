'use strict';

angular.module('watererpApp').factory(
		'OnlinePaymentResponse',
		function($resource, DateUtils) {
			return $resource('api/onlinePaymentResponses/:id', {}, {
				'query' : {
					method : 'GET',
					isArray : true
				},
				'get' : {
					method : 'GET',
					transformResponse : function(data) {
						data = angular.fromJson(data);
						data.responseTime = DateUtils
								.convertDateTimeFromServer(data.responseTime);
						return data;
					}
				},
				'update' : {
					method : 'PUT'
				}
			});
		}).factory(
		'OnlinePaymentResponseSvc',
		function($http) {
			return {
				findByOrder : function(orderId) {
					return $http.get('api/onlinePaymentResponses', {
						params : {
							orderId : orderId
						}
					}).then(function successCallback(response) {
						return response.data;
					}, function errorCallback(response) {
						return "error";
					});
				}
			};
		});
