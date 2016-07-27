'use strict';

angular.module('watererpApp').controller('ChargeBaseDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ChargeBase',
        function($scope, $stateParams, $uibModalInstance, entity, ChargeBase) {

        $scope.chargeBase = entity;
        $scope.load = function(id) {
            ChargeBase.get({id : id}, function(result) {
                $scope.chargeBase = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:chargeBaseUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.chargeBase.id != null) {
                ChargeBase.update($scope.chargeBase, onSaveSuccess, onSaveError);
            } else {
                ChargeBase.save($scope.chargeBase, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
