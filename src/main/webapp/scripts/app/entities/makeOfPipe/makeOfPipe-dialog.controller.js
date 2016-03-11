'use strict';

angular.module('watererpApp').controller('MakeOfPipeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MakeOfPipe',
        function($scope, $stateParams, $uibModalInstance, entity, MakeOfPipe) {

        $scope.makeOfPipe = entity;
        $scope.load = function(id) {
            MakeOfPipe.get({id : id}, function(result) {
                $scope.makeOfPipe = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:makeOfPipeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.makeOfPipe.id != null) {
                MakeOfPipe.update($scope.makeOfPipe, onSaveSuccess, onSaveError);
            } else {
                MakeOfPipe.save($scope.makeOfPipe, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
