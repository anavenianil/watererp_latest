'use strict';

angular.module('watererpApp').controller('ItemRequiredDialogController',
        function($scope, $stateParams, $state, ItemRequired, MaterialMaster, ApplicationTxn, FeasibilityStudy, Proceedings, Uom, ParseLinks,
        		ApplicationTxnService, ProceedingsService) {

        $scope.itemRequired = {};
        $scope.materialmasters = MaterialMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.feasibilitystudys = FeasibilityStudy.query();
        $scope.proceedingss = Proceedings.query();
        $scope.uoms = Uom.query();
        $scope.itemRequired.itemRequiredArr = [];
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        $scope.proceedings = {};
       
        
        $scope.load = function(id) {
            ItemRequired.get({id : id}, function(result) {
                $scope.itemRequired = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        $scope.arr = [];
        $scope.makeArray = function () {
        	$scope.itemRequired.itemrequiredArr = [];
            $scope.arr.length = 0;
            for (var i = 0; i < parseInt($scope.itemRequireds.length); i++) {
                $scope.arr.push(i);
                $scope.itemRequired.itemrequiredArr[i] = {};
                $scope.itemRequired.itemrequiredArr[i].id;
                $scope.itemRequired.itemrequiredArr[i].provided;
                $scope.itemRequired.itemrequiredArr[i].quantity;
                $scope.itemRequired.itemrequiredArr[i].materialMaster = {};
                $scope.itemRequired.itemrequiredArr[i].materialMaster.id;
                $scope.itemRequired.itemrequiredArr[i].uom={};
                $scope.itemRequired.itemrequiredArr[i].uom.id={};
                $scope.itemRequired.itemrequiredArr[i].amount;
                $scope.itemRequired.itemrequiredArr[i].ratePerShs;
                $scope.itemRequired.itemrequiredArr[i].proceedings ={};
                $scope.itemRequired.itemrequiredArr[i].proceedings.id;
                $scope.itemRequired.itemrequiredArr[i].applicationTxn = {};
                $scope.itemRequired.itemrequiredArr[i].applicationTxn.id;
            }
        };
        
        $scope.display = function(){
        	for(var i=0; i<$scope.itemRequireds.length;i++){
        		$scope.itemRequired.itemRequiredArr[i] = {};
        		$scope.itemRequired.itemRequiredArr[i] = $scope.itemRequireds[i];
        		$scope.itemRequired.itemRequiredArr[i].provided = $scope.itemRequireds[i].quantity;
        	}
        }
        
        $scope.getProceedings = function(){
	        ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
	            $scope.proceedings = result;
	        });
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
            		$scope.makeArray();
            		$scope.display();
            		$scope.getProceedings();
            	});
        }
        


        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemRequiredUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	ApplicationTxnService.approveRequest($scope.applicationTxnId, $scope.itemRequired.remarks);
        	 $scope.proceedings.itemRequireds = [];
        	 $state.go('applicationTxn');
        	for (var item in $scope.itemRequired.itemRequiredArr) {
        		/*$scope.itemRequired[item] = {};
        		$scope.itemRequired[item] = $scope.itemRequired.itemRequiredArr[item];
        		ItemRequired.update($scope.itemRequired[item], onSaveSuccess, onSaveError);*/
        		$scope.proceedings.itemRequireds.push($scope.itemRequired.itemRequiredArr[item]);
        	}
        	
        	if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                //Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
            /*$scope.isSaving = true;
            if ($scope.itemRequired.id != null) {
                ItemRequired.update($scope.itemRequired, onSaveSuccess, onSaveError);
            } else {
                ItemRequired.save($scope.itemRequired, onSaveSuccess, onSaveError);
            }*/
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
