'use strict';

angular.module('watererpApp').controller('DocketCodeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DocketCode',
        function($scope, $stateParams, $uibModalInstance, entity, DocketCode) {

        $scope.docketCode = entity;
        $scope.load = function(id) {
            DocketCode.get({id : id}, function(result) {
                $scope.docketCode = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:docketCodeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.docketCode.id != null) {
                DocketCode.update($scope.docketCode, onSaveSuccess, onSaveError);
            } else {
                DocketCode.save($scope.docketCode, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
