'use strict';

angular.module('watererpApp').controller('ConnectionTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ConnectionTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, ConnectionTypeMaster) {

        $scope.connectionTypeMaster = entity;
        $scope.load = function(id) {
            ConnectionTypeMaster.get({id : id}, function(result) {
                $scope.connectionTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:connectionTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.connectionTypeMaster.id != null) {
                ConnectionTypeMaster.update($scope.connectionTypeMaster, onSaveSuccess, onSaveError);
            } else {
                ConnectionTypeMaster.save($scope.connectionTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
