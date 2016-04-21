'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerComplaintsDialogController',
				function($scope, $state, CustomerComplaints, ParseLinks,
						$stateParams, ApplicationTxn, ComplaintTypeMaster,
						UploadUtil, CustDetailsService, $http) {
					$scope.customerComplaints = {};
					// This code is to per populate system date in Complaint Date Field
					$scope.customerComplaints.complaintDate = new Date();
					$scope.applicationtxns = ApplicationTxn.query();
					$scope.complainttypemasters = ComplaintTypeMaster.query();
					$scope.load = function(id) {
						CustomerComplaints.get({
							id : id
						}, function(result) {
							$scope.customerComplaints = result;
						});
					};

					if ($stateParams.id != null) {
						$scope.load($stateParams.id);
					}
					
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
					$scope.collDetails = {};
					$scope.collDetails.consName = "";
					$scope.customerComplaints.custDetails = {};
					$scope.onSelect = function($item, $model, $label) {
						console.log($item);
						var arr = $item.split("-");
						
						$scope.collDetails.can = arr[0];
						$scope.collDetails.consName = arr[1];
						$scope.collDetails.address = arr[2];
						$scope.custInfo = "";
						$scope.isValidCust = true;
						
						CustDetailsService.get({
							can : $scope.collDetails.can
						}, function(result) {
							$scope.custDetails = result;
							$scope.customerComplaints.custDetails.consName = $scope.custDetails.consName;
							$scope.customerComplaints.can = $scope.custDetails.can;
							$scope.customerComplaints.tariffCategory = $scope.custDetails.tariffCategoryMaster.tariffCategory;
						});
					};

					var onSaveSuccess = function(result) {
						$scope.$emit('watererpApp:customerComplaintsUpdate',
								result);
						// $uibModalInstance.close(result);
						$scope.isSaving = false;
						$state.go('customerComplaints');
					};

					var onSaveError = function(result) {
						$scope.isSaving = false;
					};

					$scope.save = function() {
						$scope.isSaving = true;
						if ($scope.customerComplaints.id != null) {
							CustomerComplaints.update(
									$scope.customerComplaints, onSaveSuccess,
									onSaveError);
						} else {
							CustomerComplaints.save($scope.customerComplaints,
									onSaveSuccess, onSaveError);
						}
					};

					$scope.clear = function() {
						$uibModalInstance.dismiss('cancel');
					};
					$scope.datePickerForComplaintDate = {};

					$scope.datePickerForComplaintDate.status = {
						opened : false
					};

					$scope.datePickerForComplaintDateOpen = function($event) {
						$scope.datePickerForComplaintDate.status.opened = true;
					};
					
					
					

					//$scope.customerComplaints.applicationTxn = {};
					// $scope.customerComplaints.connectionTypeMaster = {};
					//$scope.customerComplaints.applicationTxn.categoryMaster = {};
					/*$scope.getApplicationTxn = function(fileNo) {
						ApplicationTxn
								.get(
										{
											id : fileNo
										},
										function(result) {
											$scope.applicationTxn = result;
											$scope.customerComplaints.applicationTxn.categoryMaster.categoryName = $scope.applicationTxn.categoryMaster.categoryName;
											$scope.customerComplaints.applicationTxn.fullName = $scope.collDetails.consName;
											$scope.customerComplaints.applicationTxn.street = $scope.applicationTxn.street;
											// $scope.customerComplaints.connectionTypeMaster.id
											// =
											// $scope.applicationTxn.connectionTypeMaster.id;
											$scope.customerComplaints.applicationTxn.constituency = $scope.applicationTxn.constituency;
											$scope.customerComplaints.applicationTxn.area = $scope.applicationTxn.area;
										});
					}*/

					$scope.$watch('customerComplaints.relevantDoc1', function(
							files) {
						$scope.formUpload = false;
						if (files != null) {
							for (var i = 0; i < files.length; i++) {
								$scope.errorMsg = null;
								(function(file) {
									UploadUtil.uploadUsingUpload(file, $scope,
											'waterErp');
								})(files[i]);
							}
						}
					});

					$scope.getReqParams = function() {
						return $scope.generateErrorOnServer ? '?errorCode='
								+ $scope.serverErrorCode + '&errorMessage='
								+ $scope.serverErrorMsg : '';
					};

				});

/*
 * 'use strict';
 * 
 * angular.module('watererpApp').controller('CustomerComplaintsDialogController',
 * ['$scope', '$stateParams', '$uibModalInstance', 'entity',
 * 'CustomerComplaints', 'ApplicationTxn', 'ComplaintTypeMaster',
 * function($scope, $stateParams, $uibModalInstance, entity, CustomerComplaints,
 * ApplicationTxn, ComplaintTypeMaster) {
 * 
 * $scope.customerComplaints = entity; $scope.applicationtxns =
 * ApplicationTxn.query(); $scope.complainttypemasters =
 * ComplaintTypeMaster.query(); $scope.load = function(id) {
 * CustomerComplaints.get({id : id}, function(result) {
 * $scope.customerComplaints = result; }); };
 * 
 * var onSaveSuccess = function (result) {
 * $scope.$emit('watererpApp:customerComplaintsUpdate', result);
 * $uibModalInstance.close(result); $scope.isSaving = false; };
 * 
 * var onSaveError = function (result) { $scope.isSaving = false; };
 * 
 * $scope.save = function () { $scope.isSaving = true; if
 * ($scope.customerComplaints.id != null) {
 * CustomerComplaints.update($scope.customerComplaints, onSaveSuccess,
 * onSaveError); } else { CustomerComplaints.save($scope.customerComplaints,
 * onSaveSuccess, onSaveError); } };
 * 
 * $scope.clear = function() { $uibModalInstance.dismiss('cancel'); }; }]);
 */