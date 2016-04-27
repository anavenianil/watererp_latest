'use strict';

angular
		.module('watererpApp')
		.factory(
				'BillFullDetails',
				function($resource, DateUtils) {
					return $resource(
							'api/billFullDetailss/:id',
							{},
							{
								'query' : {
									method : 'GET',
									isArray : true
								},
								'get' : {
									method : 'GET',
									transformResponse : function(data) {
										data = angular.fromJson(data);
										data.connDate = DateUtils
												.convertLocaleDateFromServer(data.connDate);
										data.prevBillMonth = DateUtils
												.convertLocaleDateFromServer(data.prevBillMonth);
										data.metReadingDt = DateUtils
												.convertLocaleDateFromServer(data.metReadingDt);
										data.metReadingMo = DateUtils
												.convertLocaleDateFromServer(data.metReadingMo);
										data.lastPymtDt = DateUtils
												.convertLocaleDateFromServer(data.lastPymtDt);
										data.billDate = DateUtils
												.convertLocaleDateFromServer(data.billDate);
										data.meterFixDate = DateUtils
												.convertLocaleDateFromServer(data.meterFixDate);
										return data;
									}
								},
								'update' : {
									method : 'PUT',
									transformRequest : function(data) {
										data.connDate = DateUtils
												.convertLocaleDateToServer(data.connDate);
										data.prevBillMonth = DateUtils
												.convertLocaleDateToServer(data.prevBillMonth);
										data.metReadingDt = DateUtils
												.convertLocaleDateToServer(data.metReadingDt);
										data.metReadingMo = DateUtils
												.convertLocaleDateToServer(data.metReadingMo);
										data.lastPymtDt = DateUtils
												.convertLocaleDateToServer(data.lastPymtDt);
										data.billDate = DateUtils
												.convertLocaleDateToServer(data.billDate);
										data.meterFixDate = DateUtils
												.convertLocaleDateToServer(data.meterFixDate);
										return angular.toJson(data);
									}
								},
								'save' : {
									method : 'POST',
									transformRequest : function(data) {
										data.connDate = DateUtils
												.convertLocaleDateToServer(data.connDate);
										data.prevBillMonth = DateUtils
												.convertLocaleDateToServer(data.prevBillMonth);
										data.metReadingDt = DateUtils
												.convertLocaleDateToServer(data.metReadingDt);
										data.metReadingMo = DateUtils
												.convertLocaleDateToServer(data.metReadingMo);
										data.lastPymtDt = DateUtils
												.convertLocaleDateToServer(data.lastPymtDt);
										data.billDate = DateUtils
												.convertLocaleDateToServer(data.billDate);
										data.meterFixDate = DateUtils
												.convertLocaleDateToServer(data.meterFixDate);
										return angular.toJson(data);
									}
								}
							});
				}).factory(
				'BillFullDetailsSvc',
				function($http) {
					return {
						findByCanAndBillDate : function(can, billDate) {

							var formatDate = function(dateToFormat) {
								if (dateToFormat !== undefined
										&& !angular.isString(dateToFormat)) {
									return dateToFormat.getYear() + '-'
											+ dateToFormat.getMonth() + '-'
											+ dateToFormat.getDay();
								}
								return dateToFormat;
							};

							return $http.get('api/billFullDetailss', {
								params : {
									can : can,
									billDate : formatDate(billDate)
								}
							}).then(function successCallback(response) {
								return response.data;
							}, function errorCallback(response) {
								return "error";
							});
						}
					};
				});
