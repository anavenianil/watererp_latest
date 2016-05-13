'use strict';

angular.module('watererpApp')
    .controller('ProceedingsDetailController', function ($scope, $rootScope, $stateParams, Proceedings, PercentageMaster, 
    		ApplicationTxn, ItemRequired, ParseLinks, ApplicationTxnService, $state, ProceedingsService) {
        $scope.proceedings = {};
        $scope.approvalDetails = {};
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        
        $scope.load = function (id) {
            Proceedings.get({id: id}, function(result) {
                $scope.proceedings = result;
            });
        };
        
        /*if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }*/
        
        if($stateParams.applicationTxnId !=null){
        	ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
                $scope.proceedings = result;
                $scope.itemRequireds = $scope.proceedings.itemRequireds;
            });
        }
        
        
        
        
        /*if($stateParams.id != null){
        	$scope.itemRequireds = [];
        	ItemRequired.query({page: $scope.page, size: 20,
        		proceedingsId : $stateParams.id}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemRequireds.push(result[i]);
                }
            });
        }*/
        
       /* if($stateParams.applicationTxnId != null){
        	$scope.itemRequireds = [];
        	ProceedingsService.get({applicationTxn: $stateParams.applicationTxnId}, function(result) {
                $scope.proceedings = result;
                //$scope.itemRequireds = $scope.proceedings.itemRequireds;
            });
        	ItemRequired.query({page: $scope.page, size: 20,
        		applicationTxnId : $stateParams.applicationTxnId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemRequireds.push(result[i]);
                }
            });
        }
        */
        
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

    });
