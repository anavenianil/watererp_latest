'use strict';

angular.module('watererpApp').controller('ItemRequiredDialogController',
        function($scope, $stateParams, $state, ItemRequired, MaterialMaster, ApplicationTxn, FeasibilityStudy, Proceedings, Uom, ParseLinks) {

        $scope.itemRequired = {};
        $scope.materialmasters = MaterialMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.feasibilitystudys = FeasibilityStudy.query();
        $scope.proceedingss = Proceedings.query();
        $scope.uoms = Uom.query();
        
        $scope.load = function(id) {
            ItemRequired.get({id : id}, function(result) {
                $scope.itemRequired = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        $scope.createItemArr = [];
        if($stateParams.applicationTxnId !=null){
        	$scope.itemRequireds =[];
        	console.log($stateParams.applicationTxnId);
        	ItemRequired.query({page: $scope.page, size: 20, 
            	applicationTxnId : $stateParams.applicationTxnId}, function(result, headers) {
            		$scope.links = ParseLinks.parse(headers('link'));
            		for (var i = 0; i < result.length; i++) {
            			$scope.itemRequireds.push(result[i]);
            		}
            		/*for(var i=0;i<$scope.itemRequireds.length; i++){
            			$scope.itemRequired[i] = $scope.itemRequireds[i];
            			//$("#field_quantity_"+i).val($scope.itemRequired[i].quantity);
            		}*/
            	});
        }
        
        /*$scope.createItemArr = function(){
       		$scope.itemArr.push($scope.count);
       		$scope.proceedings.itemRequireds[$scope.count]= {};
       		$scope.count = $scope.count +1;
        }*/

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemRequiredUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	/*for(var i=0;i<$scope.itemRequireds.length;i++){
        		var id ="id_"+i;
        		var quantity = "field_quantity_"+i;
        		var ratePerShs  = "field_ratePerShs_"+i;
        		var amount  = "field_amount_"+i;
        		var materialMaster  = "field_materialMaster_"+i;
        		var applicationTxn  = "field_applicationTxn_"+i;
        		var proceedings  = "field_proceedings_"+i;
        		var uom  = "field_uom_"+i;
        		var provided = "field_provided_"+i;
        		$scope.itemRequired.provided = document.getElementById(provided).value;
        		$scope.itemRequired.quantity = document.getElementById(quantity).value;
        		$scope.itemRequired.ratePerShs = document.getElementById(ratePerShs).value;
        		$scope.itemRequired.amount = document.getElementById(amount).value;
        		$scope.itemRequired.materialMaster = {};
        		$scope.itemRequired.materialMaster.id = document.getElementById(materialMaster).value;
        		$scope.itemRequired.applicationTxn = {};
        		$scope.itemRequired.applicationTxn.id = document.getElementById(applicationTxn).value;
        		$scope.itemRequired.proceedings = {}
        		$scope.itemRequired.proceedings.id = document.getElementById(proceedings).value;
        		$scope.itemRequired.uom = {};
        		$scope.itemRequired.uom.id = document.getElementById(uom).value;
        		$scope.itemRequired.provided = document.getElementById(provided).value;
        		//$scope.itemRequired.provided = document.getElementById("field_provided_"+i).value;
        		ItemRequired.update($scope.itemRequired, onSaveSuccess, onSaveError);
        	}*/
            $scope.isSaving = true;
            if ($scope.itemRequired.id != null) {
                ItemRequired.update($scope.itemRequired, onSaveSuccess, onSaveError);
            } else {
                ItemRequired.save($scope.itemRequired, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
});
