'use strict';

angular.module('watererpApp').controller('SchemeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'SchemeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, SchemeMaster) {

        $scope.schemeMaster = entity;
        $scope.load = function(id) {
            SchemeMaster.get({id : id}, function(result) {
                $scope.schemeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:schemeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.schemeMaster.id != null) {
                SchemeMaster.update($scope.schemeMaster, onSaveSuccess, onSaveError);
            } else {
                SchemeMaster.save($scope.schemeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
