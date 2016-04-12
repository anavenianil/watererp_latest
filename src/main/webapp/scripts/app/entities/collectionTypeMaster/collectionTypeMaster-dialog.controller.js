'use strict';

angular.module('watererpApp').controller('CollectionTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CollectionTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CollectionTypeMaster) {

        $scope.collectionTypeMaster = entity;
        $scope.load = function(id) {
            CollectionTypeMaster.get({id : id}, function(result) {
                $scope.collectionTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:collectionTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.collectionTypeMaster.id != null) {
                CollectionTypeMaster.update($scope.collectionTypeMaster, onSaveSuccess, onSaveError);
            } else {
                CollectionTypeMaster.save($scope.collectionTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
