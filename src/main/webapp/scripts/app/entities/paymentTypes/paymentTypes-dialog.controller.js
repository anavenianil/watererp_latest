'use strict';

angular.module('watererpApp').controller('PaymentTypesDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'PaymentTypes',
        function($scope, $stateParams, $uibModalInstance, entity, PaymentTypes) {

        $scope.paymentTypes = entity;
        $scope.load = function(id) {
            PaymentTypes.get({id : id}, function(result) {
                $scope.paymentTypes = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:paymentTypesUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.paymentTypes.id != null) {
                PaymentTypes.update($scope.paymentTypes, onSaveSuccess, onSaveError);
            } else {
                PaymentTypes.save($scope.paymentTypes, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
