'use strict';

angular.module('watererpApp').controller('UomDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Uom',
        function($scope, $stateParams, $uibModalInstance, entity, Uom) {

        $scope.uom = entity;
        $scope.load = function(id) {
            Uom.get({id : id}, function(result) {
                $scope.uom = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:uomUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.uom.id != null) {
                Uom.update($scope.uom, onSaveSuccess, onSaveError);
            } else {
                Uom.save($scope.uom, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
