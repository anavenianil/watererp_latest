'use strict';

angular.module('watererpApp').controller('WorkflowTxnDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowTxnDetails', 'RequestMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowTxnDetails, RequestMaster) {

        $scope.workflowTxnDetails = entity;
        $scope.requestmasters = RequestMaster.query();
        $scope.load = function(id) {
            WorkflowTxnDetails.get({id : id}, function(result) {
                $scope.workflowTxnDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowTxnDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowTxnDetails.id != null) {
                WorkflowTxnDetails.update($scope.workflowTxnDetails, onSaveSuccess, onSaveError);
            } else {
                WorkflowTxnDetails.save($scope.workflowTxnDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
