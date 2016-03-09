'use strict';

angular.module('watererpApp').controller('UrlDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Url',
        function($scope, $stateParams, $uibModalInstance, entity, Url) {

        $scope.url = entity;
        $scope.load = function(id) {
            Url.get({id : id}, function(result) {
                $scope.url = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:urlUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.url.id != null) {
                Url.update($scope.url, onSaveSuccess, onSaveError);
            } else {
                Url.save($scope.url, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
