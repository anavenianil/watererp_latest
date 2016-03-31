'use strict';

angular.module('watererpApp').controller('ProceedingsDialogController',
        function($scope, $state, $stateParams, Proceedings, PercentageMaster, ApplicationTxn, ItemRequired, MaterialMaster) {

        $scope.proceedings = {};
        //$scope.percentagemasters = PercentageMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.itemrequireds = ItemRequired.query();
        $scope.materialmasters = MaterialMaster.query();
        $scope.proceedings.itemRequired = {};
        
        
        $scope.loadAllPercentageMaster = function() {
        	$scope.percentagemasters = [];
            PercentageMaster.query({page: $scope.page, size: 20}, function(result, headers) {
                for (var i = 0; i < result.length; i++) {
                    $scope.percentagemasters.push(result[i]);
                }
                $.each($scope.percentagemasters, function(){
                	console.log(this.percentType);
                	if(this.percentType == "Supervision"){
                		$scope.proceedings.supervisionPercentText = this.percentType;
                		$scope.proceedings.supervisionPercent = this.percentValue;
                	}
                	if(this.percentType == "Labour Charges"){
                		$scope.proceedings.labourChargePercentText= this.percentType;
                		$scope.proceedings.labourChargePercent = this.percentValue;
                	}
                	if(this.percentType == "Site Survey"){
                		$scope.proceedings.siteSurveyPercentText= this.percentType;
                		$scope.proceedings.siteSurveyPercent = this.percentValue;
                	}
                	if(this.percentType == "Connection Fee of A & B"){
                		$scope.proceedings.connectionFeePercentText= this.percentType;
                		$scope.proceedings.connectionFeePercent = this.percentValue;
                	}
  			   });
            });
        };
        $scope.loadAllPercentageMaster();
        
        $scope.load = function(id) {
            Proceedings.get({id : id}, function(result) {
                $scope.proceedings = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:proceedingsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('proceedings');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        
        $scope.proceedings.itemRequireds = [];
        $scope.count = 0;
        $scope.itemArr = [];
        $scope.createItemArr = function(){
       		$scope.itemArr.push($scope.count);
       		$scope.proceedings.itemRequireds[$scope.count]= {};
       		$scope.count = $scope.count +1;
        }
        
        $scope.getMaterialDetail = function(id, indexId){
        	MaterialMaster.get({id : id}, function(result) {
                $scope.materialMaster = result;
                $scope.proceedings.itemRequireds[indexId].ratePerShs = $scope.materialMaster.unitRate;
            });
        }
        
        $scope.proceedings.subTotalA = 0;
        $scope.calculateRate = function(quantity, rate, indexId){
        	$scope.proceedings.itemRequireds[indexId].amount = quantity * rate;	
        	$scope.proceedings.subTotalA = $scope.proceedings.subTotalA + $scope.proceedings.itemRequireds[indexId].amount;
        	console.log($scope.proceedings.subTotalA);
        	
        	
        	
        	$scope.proceedings.supervisionCharge = ($scope.proceedings.subTotalA * $scope.proceedings.supervisionPercent)/100;
        	$scope.proceedings.labourCharge = ($scope.proceedings.subTotalA * $scope.proceedings.labourChargePercent)/100;
        	$scope.proceedings.siteSurvey = ($scope.proceedings.subTotalA * $scope.proceedings.siteSurveyPercent)/100;
        	$scope.proceedings.subTotalB = $scope.proceedings.subTotalA  + $scope.proceedings.supervisionCharge 
        										+ $scope.proceedings.labourCharge +$scope.proceedings.siteSurvey;
        	$scope.proceedings.connectionFee = ($scope.proceedings.subTotalB * $scope.proceedings.connectionFeePercent)/100;
        	$scope.proceedings.applicationFormFee = 1000;
        	$scope.proceedings.grandTotal = $scope.proceedings.subTotalB + $scope.proceedings.connectionFee 
        									+ $scope.proceedings.applicationFormFee;
        	
        }
        
});
