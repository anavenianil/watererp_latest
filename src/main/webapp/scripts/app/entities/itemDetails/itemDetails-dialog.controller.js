'use strict';

angular.module('watererpApp').controller('ItemDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ItemDetails',
        function($scope, $stateParams, $uibModalInstance, entity, ItemDetails) {

        $scope.itemDetails = entity;
        $scope.load = function(id) {
            ItemDetails.get({id : id}, function(result) {
                $scope.itemDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemDetails.id != null) {
                ItemDetails.update($scope.itemDetails, onSaveSuccess, onSaveError);
            } else {
                ItemDetails.save($scope.itemDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
