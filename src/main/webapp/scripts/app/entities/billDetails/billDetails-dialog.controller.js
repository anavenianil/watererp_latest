'use strict';

angular
		.module('watererpApp')
		.controller(
				'BillDetailsDialogController',
				function($scope, $state, BillDetails, CustDetails,
						CustDetailsService, ParseLinks, $stateParams, $http,
						User) {

					$scope.billDetailss = [];
					$scope.predicate = 'id';
					$scope.billDetails = {};
					$scope.currentBillTypes = [ {
						id : 'M',
						name : 'METERED'
					}, {
						id : 'S',
						name : 'STUCK'
					}, {
						id : 'L',
						name : 'LOCKED'
					}, {
						id : 'B',
						name : 'BURNT'
					} ];
					$scope.users = User.query();

					$scope.billDetailsId = $stateParams.id;
					if ($stateParams.id != null) {
						BillDetails.get({
							id : $scope.billDetailsId
						}, function(result) {
							$scope.billDetails = result;
							$scope.getCustDetails($scope.billDetails.can);
						});
					}

					var onSaveSuccess = function(result) {
						$scope.$emit('watererpApp:billDetailsUpdate', result);
						$scope.isSaving = false;
						$scope.billDetails.id = result.id;
						$state.go('billDetails');
					};

					var onSaveError = function(result) {
						$scope.isSaving = false;
					};

					$scope.save = function() {
						$scope.isSaving = true;
						if ($scope.billDetails.id != null) {
							BillDetails.update($scope.billDetails,
									onSaveSuccess, onSaveError);
						} else {
							BillDetails.save($scope.billDetails, onSaveSuccess,
									onSaveError);
						}
					};

					$scope.refresh = function() {
						$scope.reset();
						$scope.clear();
					};

					$scope.getLocation = function(val) {
						$scope.isValidCust = false;

						return $http.get('api/custDetailss/searchCAN/' + val, {
							params : {
								address : val,
								sensor : false
							}
						}).then(function(response) {
							var res = response.data.map(function(item) {
								return item;
							});

							return res;
						});
					}

					$scope.onSelect = function($item, $model, $label) {
						var arr = $item.split("-");
						$scope.billDetails.can = arr[0];
						$scope.billDetails.consName = arr[1];
						$scope.billDetails.address = arr[2];
						$scope.custInfo = "";
						$scope.getCustDetails($scope.billDetails.can);
						$scope.isValidCust = true;
					};
					
					$scope.datePickerForMetReadingDt = {};

			        $scope.datePickerForMetReadingDt.status = {
			            opened: false
			        };

			        $scope.datePickerForMetReadingDtOpen = function($event) {
			            $scope.datePickerForMetReadingDt.status.opened = true;
			        };

					$scope.getCustDetails = function(can) {
						CustDetailsService
								.get(
										{
											can : can
										},
										function(result) {
											$scope.custDetails = result;
											$scope.billDetails.consName = $scope.custDetails.consName;
											$scope.billDetails.can = $scope.custDetails.can;
											$scope.billDetails.address = $scope.custDetails.address;
											$scope.billDetails.prevBillMonth = $scope.custDetails.prevBillMonth;
											if ($scope.billDetails.prevBillMonth == null) {
												$scope.billDetails.fromMonth = $scope.custDetails.meterFixDate;
												$scope.billDetails.initialReading = $scope.custDetails.prevReading;
												$scope.billDetails.prevMetReadingDt = $scope.custDetails.meterFixDate;
											} else {
												var date2 = new Date(
														$scope.billDetails.prevBillMonth);
												$scope.billDetails.fromMonth = date2.getFullYear() + date2.getMonth();
												$scope.billDetails.initialReading = $scope.custDetails.prevReading;
												$scope.billDetails.prevMetReadingDt = $scope.custDetails.metReadingDt;
											}

										});
					}
				});