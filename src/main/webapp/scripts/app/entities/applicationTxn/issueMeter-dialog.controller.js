'use strict';

angular.module('watererpApp')
    .controller('IssueMeterDialogController', function ($scope, $state, $stateParams, ApplicationTxn, ParseLinks, DateUtils, MeterDetails,
    		ApplicationTxnService) {

    	$scope.applicationTxn = {};
    	//$scope.meterdetailss = MeterDetails.query();
    	$scope.applicationTxn.connectionDate = new Date();
    	$scope.dtmax = new Date();
    	
    	
    	$scope.loadAllUnallottedMeter = function() {
    		$scope.meterdetailss = [];
            MeterDetails.query({page: $scope.page, size: 20, meterStatusId: 2}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.meterdetailss.push(result[i]);
                }
            });
        };
        $scope.loadAllUnallottedMeter();
    	
    	$scope.load = function(id){
    		ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
                $scope.applicationTxn.remarks = "";
            });
    	}
    	if($stateParams.id != null){
               $scope.load($stateParams.id); 
         }
    	
    	if($stateParams.applicationTxnId != null){
            $scope.load($stateParams.applicationTxnId);
    	}
 	
    	
    	
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } /*else {
                //ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            }*/
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForConnectionDate = {};

        $scope.datePickerForConnectionDate.status = {
            opened: false
        };

        $scope.datePickerForConnectionDateOpen = function($event) {
            $scope.datePickerForConnectionDate.status.opened = true;
        };
        
        $scope.clear = function(){
        	$scope.applicationTxn = {
            };
        }
    });




