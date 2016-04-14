'use strict';

angular.module('watererpApp').controller(
		'CollDetailsDialogController',
		function($scope, $state, CollDetails, ParseLinks, $stateParams,
				PaymentTypes, InstrumentIssuerMaster, CustDetails,
				CustDetailsService, CollectionTypeMaster, $http) {

			$scope.instrEnabled = true;
			$scope.paymenttypess = PaymentTypes.query();
			$scope.instrumentissuermasters = InstrumentIssuerMaster.query();
			$scope.collectionTypeMasters = CollectionTypeMaster.query();

			$scope.custDetails = {};
			$scope.collDetails = {};
			$scope.collDetails.custDetails = {};

			$scope.collDetails.receiptDt = new Date();

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:collDetailsUpdate', result);
				$scope.isSaving = false;
				$state.go('collDetails');
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};
			
			 $scope.getLocation = function(val) {
				 if(val != null && val.length > 2)
				    return $http.get('api/custDetailss/searchCAN/' + val, {
				        params: {
				          address: val,
				          sensor: false
				        }
				      }).then(function(response){
				    	  var res = response.data.map(function(item){
				          return item;
				        });

					      return res;
				      });
			 }
			
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
				if ($scope.editForm.field_custDetails.$invalid) {
					$scope.clear();
					return;
				}

				CustDetailsService.get({
					can : can
				}, function(result) {
					$scope.custDetails = result;
					$scope.collDetails.consName = $scope.custDetails.consName;
					$scope.collDetails.can = $scope.custDetails.can;
				});
			}


			$scope.resetInstr = function(paymentMode) {
				if (paymentMode === 'Cash') {
					$scope.instrEnabled = false;
					$scope.collDetails.instrNo = null;
					$scope.collDetails.instrDt = null;
					$scope.collDetails.instrIssuer = null;
				} else
					$scope.instrEnabled = true;
			}
		});
