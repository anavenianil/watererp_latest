'use strict';

angular.module('watererpApp').controller('DesignationMappingsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DesignationMappings', 'DesigCategoryMaster', 'SubDesigCategoryMaster', 'DesignationMaster', 'GroupMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DesignationMappings, DesigCategoryMaster, SubDesigCategoryMaster, DesignationMaster, GroupMaster) {

        $scope.designationMappings = entity;
        $scope.desigcategorymasters = DesigCategoryMaster.query();
        $scope.subdesigcategorymasters = SubDesigCategoryMaster.query();
        $scope.designationmasters = DesignationMaster.query();
        $scope.groupmasters = GroupMaster.query();
        $scope.load = function(id) {
            DesignationMappings.get({id : id}, function(result) {
                $scope.designationMappings = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:designationMappingsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.designationMappings.id != null) {
                DesignationMappings.update($scope.designationMappings, onSaveSuccess, onSaveError);
            } else {
                DesignationMappings.save($scope.designationMappings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
