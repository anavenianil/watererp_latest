'use strict';

angular.module('watererpApp').controller('ItemRequiredDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ItemRequired', 'MaterialMaster', 'ApplicationTxn', 'FeasibilityStudy', 'Proceedings',
        function($scope, $stateParams, $uibModalInstance, entity, ItemRequired, MaterialMaster, ApplicationTxn, FeasibilityStudy, Proceedings) {

        $scope.itemRequired = entity;
        $scope.materialmasters = MaterialMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.feasibilitystudys = FeasibilityStudy.query();
        $scope.proceedingss = Proceedings.query();
        $scope.load = function(id) {
            ItemRequired.get({id : id}, function(result) {
                $scope.itemRequired = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemRequiredUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemRequired.id != null) {
                ItemRequired.update($scope.itemRequired, onSaveSuccess, onSaveError);
            } else {
                ItemRequired.save($scope.itemRequired, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
