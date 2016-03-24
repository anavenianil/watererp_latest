'use strict';

angular.module('watererpApp').controller('ZoneMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ZoneMaster', 'DivisionMaster',
        function($scope, $stateParams, $uibModalInstance, entity, ZoneMaster, DivisionMaster) {

        $scope.zoneMaster = entity;
        $scope.divisionmasters = DivisionMaster.query();
        $scope.load = function(id) {
            ZoneMaster.get({id : id}, function(result) {
                $scope.zoneMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:zoneMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.zoneMaster.id != null) {
                ZoneMaster.update($scope.zoneMaster, onSaveSuccess, onSaveError);
            } else {
                ZoneMaster.save($scope.zoneMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
