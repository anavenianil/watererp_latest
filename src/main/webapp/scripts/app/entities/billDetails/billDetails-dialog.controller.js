'use strict';

angular
		.module('watererpApp')
		.controller(
				'BillDetailsDialogController',
				function($scope, $state, $filter, BillDetails, BillDetailsSvc,
						CustDetails, CustDetailsService, ParseLinks,
						$stateParams, $http, User) {

					$scope.recordExists = false;
					$scope.alreadyRun = false;
					$scope.billDetailss = [];
					$scope.predicate = 'id';
					$scope.billDetails = {};
					$scope.billDetails.forceManual = null;
					$scope.billDetails.isRounding = false;
					$scope.isRounding = false;
					$scope.isForceManual = false;
					$scope.isMetReadingDisabled = false;
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
						$scope.billDetails.status = 'INITIATED';
						$scope.billDetails.insertDt = new Date();
						var toDate = $scope.billDetails.toMonth + "01"; //YYYYMMDD
						var pattern = /(\d{4})(\d{2})(\d{2})/;
						$scope.billDetails.billDate = new Date(toDate.replace(
								pattern, '$1-$2-$3'));

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
					$scope.clear=function()
					{
						$scope.billDetails={currentBillType:null , metReadingDt:null , presentReading:null , collectionTypeMaster:null
								};
					};

					$scope.getLocation = function(val) {
						$scope.isValidCust = false;
						$scope.recordExists = false;

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

					$scope.checkMetReadingField = function()
					{
						if($scope.billDetails.currentBillType !== "M"){
							$scope.isMetReadingDisabled = true;
							$scope.billDetails.presentReading = null;
						}
						else
							$scope.isMetReadingDisabled = false;
					}
					
					$scope.onSelect = function($item, $model, $label) {
						var arr = $item.split("-");
						$scope.billDetails.can = arr[0].trim();
						$scope.billDetails.consName = arr[1];
						$scope.billDetails.address = arr[2];
						$scope.custInfo = "";
						$scope.getCustDetails($scope.billDetails.can);
						$scope.isValidCust = true;
						$scope.clear();
						$scope.rc.editForm.attempted=false;
						$scope.editForm.$setPristine();
					};
					
					$scope.datePickerForMetReadingDt = {};

					$scope.datePickerForMetReadingDt.status = {
						opened : false
					};

					$scope.datePickerForMetReadingDtOpen = function($event) {
						$scope.datePickerForMetReadingDt.status.opened = true;
					};

					$scope.setToMonthAndCheck = function() {
						$scope.setToMonth();
						$scope.checkDates();
					}
					

					$scope.setToMonth = function() {
						$scope.billDetails.toMonth = $filter('date')(
								$scope.billDetails.metReadingDt, "yyyyMM");
					}
					

					$scope.setToMonthManual = function() {

						if ($scope.billDetails.forceManual == 1) {
							var toMonthNo = parseInt($scope.billDetails.toMonth
									.substr(0, 4), 10)
									* 12
									+ parseInt($scope.billDetails.toMonth
											.substr(4, 2), 10);
							// Subtract one month for Manual.
							var newToMonthYear = ~~((toMonthNo - 1) / 12);
							var newToMonthMonth = (toMonthNo - 1) % 12;
							$scope.billDetails.toMonth = ""
									+ newToMonthYear
									+ ""
									+ (newToMonthMonth < 10 ? "0"
											+ newToMonthMonth : newToMonthMonth);
						} else {
							$scope.setToMonth();
						}

					}

					$scope.checkPrevious = function() {
						$scope.billDetails.isRounding = false;
						if ($scope.billDetails.initialReading > $scope.billDetails.presentReading) {

							if ($scope.billDetails.initialReading > 900
									&& $scope.billDetails.presentReading < 100) {
								$scope.isRounding = true;
								$scope.editForm.presentReading.$setValidity(
										"ltPrevious", true);
								return true;
							} else {
								$scope.isRounding = false;
								$scope.editForm.presentReading.$setValidity(
										"ltPrevious", false);
								return true;
							}
						} else {
							$scope.isRounding = false;
							$scope.editForm.presentReading.$setValidity(
									"ltPrevious", true);
							return true;
						}
					}

					$scope.cancel = function() {
						BillDetailsSvc.cancelBillForCan({
							can : $scope.billDetails.can
						}).then(function(result) {
							if (result != null && result !== 'error') {
								$scope.recordExists = false;
								$scope.billDetailss = [];
								$scope.predicate = 'id';
								$scope.billDetails = {};
								$scope.isValidCust = false;
								$scope.custDetails = {};
								$scope.isMetReadingDisabled = false;
								$state.go('billDetails.new');
							}
						});
					}

					$scope.checkDates = function() {
						$scope.billDetails.forceManual = null;

						if ($scope.billDetails.fromMonth != null
								&& typeof $scope.billDetails.fromMonth != "undefined"
								&& $scope.billDetails.toMonth != null
								&& typeof $scope.billDetails.toMonth != "undefined") {

							var fromMonthNo = parseInt(
									$scope.billDetails.fromMonth.substr(0, 4),
									10)
									* 12
									+ parseInt($scope.billDetails.fromMonth
											.substr(4, 2), 10);
							var toMonthNo = parseInt($scope.billDetails.toMonth
									.substr(0, 4), 10)
									* 12
									+ parseInt($scope.billDetails.toMonth
											.substr(4, 2), 10);

							var months = toMonthNo - fromMonthNo;

							if (months > 0){
								$scope.isForceManual = true;
							}
							else{
								$scope.isForceManual = false;
							}
						} else
							{
							$scope.isForceManual = false;
							}
					}

					$scope.getCustDetails = function(can) {

						BillDetailsSvc.findByCan({
							can : can
						}).then(function(result) {
							if (result != null && result !== '') {
								$scope.billDetails = result;
								if($scope.billDetails.status === 'INITIATED')
									$scope.recordExists = true;
								else if($scope.billDetails.status === 'COMMITTED')
										$scope.alreadyRun = true;
							}
						});

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
												$scope.billDetails.fromMonth = $filter(
														'date')
														(
																$scope.custDetails.meterFixDate,
																"yyyyMM");
												$scope.billDetails.initialReading = $scope.custDetails.prevReading;
												$scope.billDetails.prevMetReadingDt = $scope.custDetails.meterFixDate;
											} else {
												$scope.billDetails.fromMonth = $filter(
														'date')
														(
																moment($scope.billDetails.prevBillMonth).add(1,'months').toDate(),
																"yyyyMM");
												$scope.billDetails.initialReading = $scope.custDetails.prevReading;
												$scope.billDetails.prevMetReadingDt = $scope.custDetails.metReadingDt;
											}

										});
					}
				});