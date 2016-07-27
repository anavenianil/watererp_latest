'use strict';

angular.module('watererpApp').controller('ReversalDetailsDialogController',
        function($scope, $stateParams, ReversalDetails, CollDetails, User, $state, $http, Principal) {

        $scope.reversalDetails = {};
        $scope.colldetailss = CollDetails.query();
        $scope.users = User.query();
        $scope.load = function(id) {
            ReversalDetails.get({id : id}, function(result) {
                $scope.reversalDetails = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
        	$('#cancelFormModal').modal('hide');
            $scope.$emit('watererpApp:reversalDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $scope.collDetailss.length = 0;
            $scope.message = "Collection Cancelled Successfully";
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reversalDetails.id != null) {
                ReversalDetails.update($scope.reversalDetails, onSaveSuccess, onSaveError);
            } else {
                ReversalDetails.save($scope.reversalDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        	$('#cancelFormModal').modal('hide');
        };
        $scope.datePickerForCancelledDate = {};

        $scope.datePickerForCancelledDate.status = {
            opened: false
        };

        $scope.datePickerForCancelledDateOpen = function($event) {
            $scope.datePickerForCancelledDate.status.opened = true;
        };
        
        
        $scope.getCollDetails = function(can) {
			$scope.isValidCust = false;
			return $http.get(
					'api/collDetailss/forCancel/' + can)
					.then(function(response) {
						$scope.collDetailss = [];
						for (var i = 0; i < response.data.length; i++) {
							$scope.collDetailss.push(response.data[i]);
						}
					});
		}
        
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
        
        $scope.onSelect = function($item, $model, $label) {
			var arr = $item.split("-");
			$scope.reversalDetails.collDetails = {};
			$scope.reversalDetails.collDetails.can = arr[0].trim();
			$scope.reversalDetails.collDetails.consName = arr[1];
			$scope.reversalDetails.collDetails.address = arr[2];
			$scope.custInfo = "";
			$scope.isValidCust = true;
			$scope.getCollDetails($scope.reversalDetails.collDetails.can);
			$scope.message = null;
		};
		
		/*$scope.assignCollDetailsId = function(collDetails){
			//alert(id);
			$scope.reversalDetails.collDetails = collDetails;
		}*/
		
		$scope.confirmCancel = function(collDetails){
			$scope.reversalDetails.collDetails = collDetails;
			$('#cancelFormModal').modal('show');
		}
});
