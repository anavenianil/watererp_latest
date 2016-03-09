'use strict';

angular.module('watererpApp').controller('MenuItemDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MenuItem',
        function($scope, $stateParams, $uibModalInstance, entity, MenuItem) {

        $scope.menuItem = entity;
        $scope.load = function(id) {
            MenuItem.get({id : id}, function(result) {
                $scope.menuItem = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:menuItemUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.menuItem.id != null) {
                MenuItem.update($scope.menuItem, onSaveSuccess, onSaveError);
            } else {
                MenuItem.save($scope.menuItem, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForModifiedDate = {};

        $scope.datePickerForModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForModifiedDateOpen = function($event) {
            $scope.datePickerForModifiedDate.status.opened = true;
        };
}]);
