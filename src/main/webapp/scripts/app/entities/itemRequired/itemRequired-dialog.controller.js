'use strict';

angular.module('watererpApp').controller('ItemRequiredDialogController',
        function($scope, $stateParams, $state, ItemRequired, MaterialMaster, ApplicationTxn, FeasibilityStudy, Proceedings, Uom, ParseLinks,
        		ApplicationTxnService, ProceedingsService) {

        $scope.itemRequired = {};
        $scope.materialmasters = MaterialMaster.query();
        $scope.uoms = Uom.query();
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        $scope.proceedings = {};
        $scope.proceedings.itemsRequireds = [];
        $scope.itemArr = [];
        
        $scope.load = function(id) {
            ItemRequired.get({id : id}, function(result) {
                $scope.itemRequired = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        $scope.getProceedings = function(){
	        ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
	            $scope.proceedings = result;
	            $scope.proceedings.applicationTxn.remarks = "";
	            for(var i=0; i< $scope.proceedings.itemRequireds.length; i++){
	        		$scope.itemArr.push(i);
	        		$scope.proceedings.itemRequireds[i].provided = $scope.proceedings.itemRequireds[i].quantity;
	        	}
	        });
        }
        $scope.getProceedings();

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemRequiredUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings);
                $state.go('applicationTxn');
            }
        };
        
        $scope.validate = function(indexVal, quantity, provide){
        	console.log(indexVal + " " + quantity + " " + provide);
        	$scope.maxVal = quantity;
        	if(provide > quantity){
        		alert("Provide can't exceed the given quantity");
        	}
        	console.log("max val:"+$scope.maxVal);
        }

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
});