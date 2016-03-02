'use strict';

angular.module('watererpApp').controller('SewerSizeController',
    ['$scope', '$stateParams', /*'$uibModalInstance', 'entity',*/ 'SewerSize', 'ParseLinks',
        function($scope, $stateParams,  /*entity,*/ SewerSize, ParseLinks) {

        $scope.sewerSize = {};//entity;
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
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
            $scope.sewerSize = {sewerSize: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
        
        
        /*$scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };*/
        
        $scope.loadAll = function() {
        	 $scope.sewerSizes = [];
        	 $('#viewSewerSizeModal').modal('show');
            SewerSize.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.sewerSizes.push(result[i]);
                }
            });
        };
        
        
        $scope.showUpdate = function (id) {
        	$('#viewSewerSizeModal').modal('hide');
           SewerSize.get({id: id}, function(result) {
               $scope.sewerSize = result;              
           });
       };
}]);
