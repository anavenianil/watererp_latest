'use strict';

angular.module('watererpApp').controller('PercentageMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'PercentageMaster',
        function($scope, $stateParams, $uibModalInstance, entity, PercentageMaster) {

        $scope.percentageMaster = entity;
        $scope.load = function(id) {
            PercentageMaster.get({id : id}, function(result) {
                $scope.percentageMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:percentageMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.percentageMaster.id != null) {
                PercentageMaster.update($scope.percentageMaster, onSaveSuccess, onSaveError);
            } else {
                PercentageMaster.save($scope.percentageMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
