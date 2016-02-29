'use strict';

angular.module('watererpApp').controller('WorkflowRelationsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowRelations', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowRelations, StatusMaster) {

        $scope.workflowRelations = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            WorkflowRelations.get({id : id}, function(result) {
                $scope.workflowRelations = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowRelationsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowRelations.id != null) {
                WorkflowRelations.update($scope.workflowRelations, onSaveSuccess, onSaveError);
            } else {
                WorkflowRelations.save($scope.workflowRelations, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
