'use strict';

angular.module('watererpApp')
    .controller('ComplaintTypeMasterDialogController', function ($scope, $state, ComplaintTypeMaster, ParseLinks, $stateParams) {


        $scope.complaintTypeMaster = {};
        $scope.load = function(id) {
            ComplaintTypeMaster.get({id : id}, function(result) {
                $scope.complaintTypeMaster = result;
            });
        };
        
        if($stateParams.id!=null){        	
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:complaintTypeMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('complaintTypeMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.complaintTypeMaster.id != null) {
                ComplaintTypeMaster.update($scope.complaintTypeMaster, onSaveSuccess, onSaveError);
            } else {
                ComplaintTypeMaster.save($scope.complaintTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    	
    });