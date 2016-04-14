'use strict';

angular.module('watererpApp').controller(
		'CollDetailsDialogController',
		function($scope, $state, CollDetails, CustDetailsSearch, ParseLinks, $stateParams,
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
				    return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
				        params: {
				          address: val,
				          sensor: false
				        }
				      }).then(function(response){
				    	  var res = response.data.results.map(function(item){
				          return item.formatted_address;
				        });
				    	  console.log("This is the response:" + JSON.stringify(res));
					      
					      return res;
				      });
				 
				 console.log("Called getLocation with val:" + val);
					if (val != null &&  val.length > 2) {
						CustDetailsSearch.query({
							searchTerm: val
						}, function(result, headers) {
						      var res = result.map(function(item){
							        return item;
							      });
						      
						      console.log("This is the response:" + JSON.stringify(res));
						      
						      return res;
						});
					} 
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
