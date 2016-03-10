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
        
        $scope.getLogin = function()
        {
        	$scope.user = Principal.getLogonUser();
        	//console.log("User is: "+JSON.stringify($scope.user));
        	if($scope.user == null){
        		$scope.navbarUserId = "";
        	}
        	else{
        		$scope.navbarUserId = $scope.user.firstName + " "+$scope.user.lastName +"("+$scope.user.login+")";
        	}
            //for navbar module and menu_items
            if ($scope.isAuthenticated()) {
            	$scope.module2MenuItems = Principal.geModuleMenus();
    		}
            else
            	$scope.module2MenuItems = {};
               
        	return $scope.isAuthenticated();
        }
        
        /*if ($scope.isAuthenticated()) {
			$http.get("/api/rest/module2MenuItems/role").success(
					function(response) {
						$scope.module2MenuItems = response;
					});
		}*/
    });
