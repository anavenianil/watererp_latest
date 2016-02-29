'use strict';

angular.module('watererpApp').controller('ConfigurationDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ConfigurationDetails',
        function($scope, $stateParams, $uibModalInstance, entity, ConfigurationDetails) {

        $scope.configurationDetails = entity;
        $scope.load = function(id) {
            ConfigurationDetails.get({id : id}, function(result) {
                $scope.configurationDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:configurationDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.configurationDetails.id != null) {
                ConfigurationDetails.update($scope.configurationDetails, onSaveSuccess, onSaveError);
            } else {
                ConfigurationDetails.save($scope.configurationDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
