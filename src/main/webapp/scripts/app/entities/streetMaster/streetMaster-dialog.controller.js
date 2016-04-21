'use strict';

angular.module('watererpApp').controller('StreetMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'StreetMaster', 'DivisionMaster',
        function($scope, $stateParams, $uibModalInstance, entity, StreetMaster, DivisionMaster) {

        $scope.streetMaster = entity;
        $scope.divisionmasters = DivisionMaster.query();
        $scope.load = function(id) {
            StreetMaster.get({id : id}, function(result) {
                $scope.streetMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:streetMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.streetMaster.id != null) {
                StreetMaster.update($scope.streetMaster, onSaveSuccess, onSaveError);
            } else {
                StreetMaster.save($scope.streetMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
