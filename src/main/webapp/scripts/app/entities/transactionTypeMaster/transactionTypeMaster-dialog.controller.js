'use strict';

angular.module('watererpApp').controller('TransactionTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TransactionTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, TransactionTypeMaster) {

        $scope.transactionTypeMaster = entity;
        $scope.load = function(id) {
            TransactionTypeMaster.get({id : id}, function(result) {
                $scope.transactionTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:transactionTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.transactionTypeMaster.id != null) {
                TransactionTypeMaster.update($scope.transactionTypeMaster, onSaveSuccess, onSaveError);
            } else {
                TransactionTypeMaster.save($scope.transactionTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
