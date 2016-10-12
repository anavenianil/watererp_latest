
'use strict';

angular.module('watererpApp').controller(
		'CollDetailsDialogController',
		function($scope, $state, CollDetails, ParseLinks, $stateParams,
				PaymentTypes, BankMaster, CustDetails,
				CustDetailsService, CollectionTypeMaster, $http) {

			$scope.isValidCust = false;
			$scope.instrEnabled = false;
			$scope.paymenttypess = PaymentTypes.query();
			$scope.bankmasters = BankMaster.query();
			//$scope.collectionTypeMasters = CollectionTypeMaster.query();

			$scope.custDetails = {};
			$scope.collDetails = {};
			$scope.collDetails.custDetails = {};
			$scope.collectionTypeMasters = [];
			$scope.collDetails.collectionTypeMaster = {};

			$scope.collDetails.receiptDt = new Date();
			
			$scope.collDetails.txnStatus = "C";
			
			CollectionTypeMaster.query({page: $scope.page, size: 20, txnType : 'C'}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.collectionTypeMasters.push(result[i]);
                }
                $scope.collDetails.collectionTypeMaster.id = $scope.collectionTypeMasters[0].id;
                $scope.collTypeId = $scope.collectionTypeMasters[0].id;
            });

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:collDetailsUpdate', result);
				$scope.isSaving = false;
				$state.go('collDetails');
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.getCustomer = function(val) {
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

			$scope.validate = function() {

				if($scope.isSaving)
					return true;
				
				if (!$scope.isValidCust)
					return true;

				if (!$scope.editForm.receiptAmt.$dirty)
					return true;

				if (!$scope.editForm.paymentTypes.$dirty)
					return true;

				/*if (!$scope.editForm.collectionTypeMaster.$dirty)
					return true;*/

				if ($scope.editForm.receiptAmt.$invalid)
					return true;

				if ($scope.editForm.paymentTypes.$invalid)
					return true;

				/*if ($scope.editForm.collectionTypeMaster.$invalid)
					return true;*/

				if ($scope.instrEnabled) {

					if (!$scope.editForm.field_instrNo.$dirty)
						return true;

					if (!$scope.editForm.field_instrDt.$dirty)
						return true;

					if (!$scope.editForm.bankMaster.$dirty)
						return true;

					if ($scope.editForm.field_instrNo.$invalid)
						return true;

					if ($scope.editForm.field_instrDt.$invalid)
						return true;

					if ($scope.editForm.bankMaster.$invalid)
						return true;

				}

				return false;

			}
			$scope.refresh = function() {
				$scope.reset();
				$scope.clear();
			};
		

			$scope.onSelect = function($item, $model, $label) {
				var arr = $item.split("-");
				$scope.collDetails = {};
				$scope.collDetails.collectionTypeMaster = {};
				$scope.collDetails.can = arr[0];
				$scope.collDetails.consName = arr[1];
				$scope.collDetails.address = arr[2];
				$scope.collDetails.receiptDt = new Date();
				//$scope.collDetails.collectionTypeMaster.id = $scope.collectionTypeMasters[0].id;
				$scope.collDetails.txnStatus = "C";
				$scope.getCustDetails(arr[0].trim());
				$scope.custInfo = "";
				$scope.isValidCust = true;
				//$scope.clear();
				$scope.collDetails.collectionTypeMaster.id = $scope.collTypeId;
				$scope.rc.editForm.attempted=false;
				$scope.editForm.$setPristine();
			};
			$scope.validateInstrDt = function() {
				var today = moment();
				var instrDt = moment($scope.collDetails.instrDt);

				var duration = moment.duration(today.diff(instrDt));
				var months = duration.asMonths();

				console.log("Months:" + months)
				var days = duration.asDays();
				if (days < -1){
					$scope.editForm.instrDt.$setValidity("future",
							false);
					$scope.editForm.instrDt.$setValidity("veryOld",
							true);
				}
				else if (months > 6) {
					$scope.editForm.instrDt.$setValidity("future",
							true);
					$scope.editForm.instrDt.$setValidity("veryOld",
							false);
				} else {
					$scope.editForm.instrDt
							.$setValidity("future", true);
					$scope.editForm.instrDt.$setValidity("veryOld",
							true);
				}

			}

			
			
			$scope.validateDate = function() {
				var today = moment();
				var receiptDt = moment($scope.collDetails.receiptDt);

				var duration = moment.duration(today.diff(receiptDt));
				var months = duration.asMonths();

				console.log("This is today:" + today);
				console.log("This is receipt dt:" + receiptDt);
				console.log("Duration:" + months);

				if (months > 6) {
					console.log("Very old, today:" + today
							+ ", receipt dt:" + receiptDt);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							false);
					$scope.editForm.receiptDt.$setValidity("future",
							true);
				} else if (months < -1) {
					console.log("Future, today:" + today
							+ ", receipt dt:" + receiptDt);
					$scope.editForm.receiptDt.$setValidity("future",
							false);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							true);
				} else {
					console.log("Ok, today:" + today + ", receipt dt:"
							+ receiptDt);
					$scope.editForm.receiptDt.$setValidity("future",
							true);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							true);
				}
			}
			
			$scope.clear=function()
			{
				$scope.collDetails={receiptAmt:null , paymentTypes:null , instrNo:null , bankMaster:null , field_instrDt:null
						};
			};
			
			

			$scope.save = function() {
				$scope.isSaving = true;

				CollDetails
						.save($scope.collDetails, onSaveSuccess, onSaveError);
			};

			$scope.datePickerForReceiptDt = {};

			$scope.datePickerForReceiptDt.status = {
				opened : false
			};

			$scope.datePickerForReceiptDtOpen = function($event) {
				$scope.datePickerForReceiptDt.status.opened = true;
			};
			$scope.datePickerForInstrDt = {};

			$scope.datePickerForInstrDt.status = {
				opened : false
			};

			$scope.datePickerForInstrDtOpen = function($event) {
				$scope.datePickerForInstrDt.status.opened = true;
			};
			$scope.datePickerForCollTime = {};

			$scope.datePickerForCollTime.status = {
				opened : false
			};

			$scope.datePickerForCollTimeOpen = function($event) {
				$scope.datePickerForCollTime.status.opened = true;
			};

			$scope.getCustDetails = function(can) {
				CustDetailsService.get({
					can : can
				}, function(result) {
					$scope.custDetails = result;
				});
			}

			$scope.resetInstr = function(paymentMode) {
				if (paymentMode.toUpperCase() === 'CASH') {
					$scope.instrEnabled = false;
					$scope.collDetails.instrNo = null;
					$scope.collDetails.instrDt = null;
					$scope.collDetails.bankMaster = null;
				} else
					$scope.instrEnabled = true;
			}
		});
