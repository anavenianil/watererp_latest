'use strict';

angular.module('watererpApp').controller('Url2RoleDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Url2Role', 'Url', 'Authority', '$http',
        function($scope, $stateParams, $uibModalInstance, entity, Url2Role, Url, Authority, $http) {

        $scope.url2Role = entity;
        //$scope.urls = Url.query();
        $scope.authoritys = Authority.query();
        $scope.load = function(id) {
            Url2Role.get({id : id}, function(result) {
                $scope.url2Role = result;
            });
        };
        
        $scope.getUrls = function() {
        	$scope.urls = [];
			return $http.get('/api/urls/getAll'
					).then(
					function(response) {
						$scope.urls = response.data;
					});
		}
        $scope.getUrls();

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:url2RoleUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.url2Role.id != null) {
                Url2Role.update($scope.url2Role, onSaveSuccess, onSaveError);
            } else {
                Url2Role.save($scope.url2Role, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
