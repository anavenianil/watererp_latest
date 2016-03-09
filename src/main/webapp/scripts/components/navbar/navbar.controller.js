'use strict';

angular.module('watererpApp')
    .controller('NavbarController', function ($scope, $http, $location, $state, Auth, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.module2MenuItems = {};
        //$scope.inProduction = ENV === 'prod';

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };
        
        if ($scope.isAuthenticated()) {
			$http.get("/api/rest/module2MenuItems/role").success(
					function(response) {
						$scope.module2MenuItems = response;
					});
		}
    });
