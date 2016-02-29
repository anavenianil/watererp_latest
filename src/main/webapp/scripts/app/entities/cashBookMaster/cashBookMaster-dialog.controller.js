'use strict';

angular.module('watererpApp').controller('CashBookMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CashBookMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CashBookMaster) {

        $scope.cashBookMaster = entity;
        $scope.load = function(id) {
            CashBookMaster.get({id : id}, function(result) {
                $scope.cashBookMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:cashBookMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.cashBookMaster.id != null) {
                CashBookMaster.update($scope.cashBookMaster, onSaveSuccess, onSaveError);
            } else {
                CashBookMaster.save($scope.cashBookMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
