'use strict';

angular.module('watererpApp').controller('StatusMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, StatusMaster) {

        $scope.statusMaster = entity;
        $scope.load = function(id) {
            StatusMaster.get({id : id}, function(result) {
                $scope.statusMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:statusMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.statusMaster.id != null) {
                StatusMaster.update($scope.statusMaster, onSaveSuccess, onSaveError);
            } else {
                StatusMaster.save($scope.statusMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
