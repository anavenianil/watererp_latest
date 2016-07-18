'use strict';

angular.module('watererpApp').controller('MenuItem2UrlDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MenuItem2Url', 'MenuItem', 'Url', '$http',
        function($scope, $stateParams, $uibModalInstance, entity, MenuItem2Url, MenuItem, Url, $http) {

        $scope.menuItem2Url = entity;
        //$scope.menuitems = MenuItem.query();
        //$scope.urls = Url.query();
        
        $scope.getMenuItems = function() {
        	$scope.menuitems = [];
			return $http.get('/api/menuItems/getAll'
					).then(
					function(response) {
						$scope.menuitems = response.data;
					});
		}
        $scope.getMenuItems();
        
        $scope.getUrls = function() {
        	$scope.urls = [];
			return $http.get('/api/urls/getAll'
					).then(
					function(response) {
						$scope.urls = response.data;
					});
		}
        $scope.getUrls();
        
        
        $scope.load = function(id) {
            MenuItem2Url.get({id : id}, function(result) {
                $scope.menuItem2Url = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:menuItem2UrlUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.menuItem2Url.id != null) {
                MenuItem2Url.update($scope.menuItem2Url, onSaveSuccess, onSaveError);
            } else {
                MenuItem2Url.save($scope.menuItem2Url, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
