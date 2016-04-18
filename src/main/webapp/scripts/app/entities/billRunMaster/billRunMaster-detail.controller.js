'use strict';

angular.module('watererpApp')
    .controller('BillRunMasterDetailController', function ($scope, $rootScope, $stateParams, entity, BillRunMaster) {
        $scope.billRunMaster = entity;
        
        $scope.load = function (id) {
            BillRunMaster.get({id: id}, function(result) {
                $scope.billRunMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billRunMasterUpdate', result);
            $scope.isSaving = false;
            $scope.load($scope.billRunMaster.id);
        };
        
        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function (status) {
            $scope.isSaving = true;
            
            if(status == null)
            	status = 'COMMIT';
            
            $scope.billRunMaster.status = status;
            if ($scope.billRunMaster.id != null) {
                BillRunMaster.update($scope.billRunMaster, onSaveSuccess, onSaveError);
            } 
        };
        
        var unsubscribe = $rootScope.$on('watererpApp:billRunMasterUpdate', function(event, result) {
            $scope.billRunMaster = result;
        });
        
        $scope.$on('$destroy', unsubscribe);

    });
