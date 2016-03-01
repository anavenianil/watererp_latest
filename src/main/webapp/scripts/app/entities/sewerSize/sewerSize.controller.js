'use strict';

angular.module('watererpApp').controller('SewerSizeController',
    ['$scope', '$stateParams', /*'$uibModalInstance', 'entity',*/ 'SewerSize',
        function($scope, $stateParams,  /*entity,*/ SewerSize) {

        $scope.sewerSize = {};//entity;
        $scope.load = function(id) {
            SewerSize.get({id : id}, function(result) {
                $scope.sewerSize = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:sewerSizeUpdate', result);
            //$uibModalInstance.close(result);
            $scope.clear();
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.sewerSize.id != null) {
                SewerSize.update($scope.sewerSize, onSaveSuccess, onSaveError);
            } else {
                SewerSize.save($scope.sewerSize, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function () {
            $scope.sewerSize = {
                sewerSize: null,
                id: null
            };
        };
        
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
