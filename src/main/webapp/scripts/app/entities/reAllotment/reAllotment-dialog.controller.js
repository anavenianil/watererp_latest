'use strict';

angular.module('watererpApp').controller('ReAllotmentDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ReAllotment', 'FileNumber', 'Customer', 'FeasibilityStatus',
        function($scope, $stateParams, $uibModalInstance, entity, ReAllotment, FileNumber, Customer, FeasibilityStatus) {

        $scope.reAllotment = entity;
        $scope.filenumbers = FileNumber.query();
        $scope.customers = Customer.query();
        $scope.feasibilitystatuss = FeasibilityStatus.query();
        $scope.load = function(id) {
            ReAllotment.get({id : id}, function(result) {
                $scope.reAllotment = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:reAllotmentUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reAllotment.id != null) {
                ReAllotment.update($scope.reAllotment, onSaveSuccess, onSaveError);
            } else {
                ReAllotment.save($scope.reAllotment, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
