'use strict';

angular.module('watererpApp').controller('FileNumberDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'FileNumber',
        function($scope, $stateParams, $uibModalInstance, entity, FileNumber) {

        $scope.fileNumber = entity;
        $scope.load = function(id) {
            FileNumber.get({id : id}, function(result) {
                $scope.fileNumber = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:fileNumberUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.fileNumber.id != null) {
                FileNumber.update($scope.fileNumber, onSaveSuccess, onSaveError);
            } else {
                FileNumber.save($scope.fileNumber, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
