'use strict';

angular.module('watererpApp').controller('WorkflowRelationshipsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowRelationships', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowRelationships, StatusMaster) {

        $scope.workflowRelationships = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            WorkflowRelationships.get({id : id}, function(result) {
                $scope.workflowRelationships = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowRelationshipsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowRelationships.id != null) {
                WorkflowRelationships.update($scope.workflowRelationships, onSaveSuccess, onSaveError);
            } else {
                WorkflowRelationships.save($scope.workflowRelationships, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
