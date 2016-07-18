'use strict';

angular.module('watererpApp').controller('ProceedingsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proceedings', 'ApplicationTxn', 'ItemRequired', 'PipeSizeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, Proceedings, ApplicationTxn, ItemRequired, PipeSizeMaster) {

        $scope.proceedings = entity;
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.itemrequireds = ItemRequired.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.load = function(id) {
            Proceedings.get({id : id}, function(result) {
                $scope.proceedings = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:proceedingsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
