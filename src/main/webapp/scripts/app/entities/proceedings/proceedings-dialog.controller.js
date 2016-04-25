
'use strict';

angular.module('watererpApp').controller('ProceedingsDialogController',
        function($scope, $state, $stateParams, Proceedings, PercentageMaster, ApplicationTxn, ItemRequired, MaterialMaster, ParseLinks, 
        		ApplicationTxnService, Uom, ConfigurationDetails) {

        $scope.proceedings = {};
        //$scope.percentagemasters = PercentageMaster.query();
        //$scope.applicationtxns = ApplicationTxn.query();
        $scope.itemrequireds = ItemRequired.query();
        $scope.materialmasters = MaterialMaster.query();
        $scope.uoms = Uom.query();
        $scope.proceedings.itemRequired = {};
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        $scope.proceedings.itemRequireds = [];
        $scope.count = 0;
        $scope.itemArr = [];
        

        $scope.getApplicationTxns = function(){
        	$scope.applicationTxns = [];
        	ApplicationTxn.query({page: $scope.page, status: 0}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
           });
        }
        $scope.getApplicationTxns();
        
        //get applicationTxn by id
        $scope.getApplicationTxn = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                $scope.proceedings.applicationTxn ={};
                $scope.proceedings.applicationTxn.id =  $scope.applicationTxn.id;
            });	
        }
        if($stateParams.applicationTxnId != null){
        	$scope.getApplicationTxn($stateParams.applicationTxnId);
        }
        
        $scope.loadAllConfigurationDetails = function() {
        	$scope.configurationDetailss = [];
        	ConfigurationDetails.query({page: $scope.page, size: 20}, function(result, headers) {
                for (var i = 0; i < result.length; i++) {
                    $scope.configurationDetailss.push(result[i]);
                }
                $.each($scope.configurationDetailss, function(){
                	//console.log(this.percentType);
                	if(this.name == "SUPERVISION"){
                		$scope.proceedings.supervisionPercentText = this.name;
                		$scope.proceedings.supervisionPercent = parseInt(this.value);
                	}
                	if(this.name == "LABOUR CHARGES"){
                		$scope.proceedings.labourChargePercentText= this.name;
                		$scope.proceedings.labourChargePercent = parseInt(this.value);
                	}
                	if(this.name == "SITE SURVEY"){
                		$scope.proceedings.siteSurveyPercentText= this.name;
                		$scope.proceedings.siteSurveyPercent = parseInt(this.value);
                	}
                	if(this.name == "CONNECTION FEE OF A & B"){
                		$scope.proceedings.connectionFeePercentText= this.name;
                		$scope.proceedings.connectionFeePercent = parseInt(this.value);
                	}
                	if(this.name == "APPLICATION FORM FEE"){
                		$scope.proceedings.applicationFormFee = parseInt(this.value);
                	}
  			   });
            });
        };
        $scope.loadAllConfigurationDetails();
        
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
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	//ApplicationTxnService.approveRequest($scope.proceedings.applicationTxn.id, $scope.proceedings.remarks);
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        };
        

        //create array for items
        $scope.createItemArr = function(){
       		$scope.itemArr.push($scope.count);
       		$scope.proceedings.itemRequireds[$scope.count]= {};
       		$scope.count = $scope.count +1;
        }
        
        
        
        //calculations for proceedings
        $scope.calculateRate = function(){
        	for(var i=0; i<$scope.itemArr.length; i++){
        		var quantity = $scope.proceedings.itemRequireds[i].quantity;
        		var rate = $scope.proceedings.itemRequireds[i].ratePerShs;
        		$scope.proceedings.itemRequireds[i].amount = quantity * rate;
        	}
        	
        	$scope.proceedings.subTotalA = 0;
        	
        	for(var i=0; i<$scope.itemArr.length; i++){
        		$scope.proceedings.subTotalA = $scope.proceedings.subTotalA + $scope.proceedings.itemRequireds[i].amount;
        	}
        	
        	$scope.proceedings.supervisionCharge = ($scope.proceedings.subTotalA * $scope.proceedings.supervisionPercent)/100;
        	$scope.proceedings.labourCharge = ($scope.proceedings.subTotalA * $scope.proceedings.labourChargePercent)/100;
        	$scope.proceedings.siteSurvey = ($scope.proceedings.subTotalA * $scope.proceedings.siteSurveyPercent)/100;
        	$scope.proceedings.subTotalB = $scope.proceedings.subTotalA  + $scope.proceedings.supervisionCharge 
        										+ $scope.proceedings.labourCharge +$scope.proceedings.siteSurvey;
        	$scope.proceedings.connectionFee = ($scope.proceedings.subTotalB * $scope.proceedings.connectionFeePercent)/100;
        	//$scope.proceedings.applicationFormFee = 1000;
        	$scope.proceedings.grandTotal = $scope.proceedings.subTotalB + $scope.proceedings.connectionFee 
        									+ $scope.proceedings.applicationFormFee;
        }
        
        //for removing items
        $scope.removeItemArr = function() {
            var lastItem = $scope.itemArr.length-1;
            $scope.count = $scope.count +1
            $scope.itemArr.splice(lastItem);
            $scope.calculateRate();
          };
          
          //to get Item detail when selected in drop down
          $scope.getMaterialDetail = function(id, indexId){
          	MaterialMaster.get({id : id}, function(result) {
                  $scope.materialMaster = result;
                  $scope.proceedings.itemRequireds[indexId].ratePerShs = $scope.materialMaster.unitRate;
                  $scope.calculateRate();
              });
          }
});
