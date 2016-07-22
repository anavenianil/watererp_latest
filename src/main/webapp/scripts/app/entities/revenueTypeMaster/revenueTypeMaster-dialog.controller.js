'use strict';

angular.module('watererpApp').controller('RevenueTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RevenueTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, RevenueTypeMaster) {

        $scope.revenueTypeMaster = entity;
        $scope.load = function(id) {
            RevenueTypeMaster.get({id : id}, function(result) {
                $scope.revenueTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:revenueTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.revenueTypeMaster.id != null) {
                RevenueTypeMaster.update($scope.revenueTypeMaster, onSaveSuccess, onSaveError);
            } else {
                RevenueTypeMaster.save($scope.revenueTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
