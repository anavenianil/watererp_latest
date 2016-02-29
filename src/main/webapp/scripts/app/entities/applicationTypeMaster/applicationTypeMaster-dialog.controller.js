'use strict';

angular.module('watererpApp').controller('ApplicationTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ApplicationTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, ApplicationTypeMaster) {

        $scope.applicationTypeMaster = entity;
        $scope.load = function(id) {
            ApplicationTypeMaster.get({id : id}, function(result) {
                $scope.applicationTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTypeMaster.id != null) {
                ApplicationTypeMaster.update($scope.applicationTypeMaster, onSaveSuccess, onSaveError);
            } else {
                ApplicationTypeMaster.save($scope.applicationTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForUpdatedDate = {};

        $scope.datePickerForUpdatedDate.status = {
            opened: false
        };

        $scope.datePickerForUpdatedDateOpen = function($event) {
            $scope.datePickerForUpdatedDate.status.opened = true;
        };
}]);
