'use strict';

angular.module('watererpApp')
    .controller('ProceedingsDetailController', function ($scope, $rootScope, $stateParams, Proceedings,  
    		ApplicationTxn, ItemRequired, ParseLinks, ApplicationTxnService, $state, ProceedingsService, ConfigurationDetails) {
        $scope.proceedings = {};
        $scope.approvalDetails = {};
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        
        $scope.load = function (id) {
            Proceedings.get({id: id}, function(result) {
                $scope.proceedings = result;
            });
        };
        
        if($stateParams.applicationTxnId !=null){
        	ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
                $scope.proceedings = result;
                $scope.itemRequireds = $scope.proceedings.itemRequireds;
            });
        }
        
        
        
        
        
        var unsubscribe = $rootScope.$on('watererpApp:proceedingsUpdate', function(event, result) {
            $scope.proceedings = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.getApplicationTxn = function (id) {
        	$scope.approvalDetails.approvedDate = new Date();
        	$scope.applicationTxn = id;
        	$('#approveModal').modal('show');
        	
        };
        
        $scope.approvalDetailsSave = function(id, remarks){
        	$('#approveModal').modal('hide');
        	//console.log(JSON.stringify($scope.approvalDetails));
        	ApplicationTxnService.approveRequest(id, remarks);
        	$state.go('applicationTxn');
        }

        $scope.loadAllConfigurationDetails = function() {
        	$scope.configurationDetailss = [];
        	ConfigurationDetails.query({page: $scope.page, size: 20}, function(result, headers) {
                for (var i = 0; i < result.length; i++) {
                    $scope.configurationDetailss.push(result[i]);
                }
                $.each($scope.configurationDetailss, function(){
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
    });
