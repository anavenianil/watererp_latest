'use strict';

angular.module('watererpApp')
    .controller('NavbarController', function ($scope, $http, $location, $state, Auth, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.module2MenuItems = {};
        $scope.orgRole = {};
        //$scope.inProduction = ENV === 'prod';

        $scope.logout = function () {
            Auth.logout();
            window.location = '/';
            $scope.userRole ="";
        };
        
        $scope.getLogin = function()
        {
        	$scope.user = Principal.getLogonUser();
        	$scope.orgRole = Principal.getOrgRole();
        	//console.log("User is: "+JSON.stringify($scope.user));
        	if($scope.user == null){
        		$scope.navbarUserId = "";
        		$scope.userRole ="";
        	}
        	else{
        		$scope.navbarUserId = $scope.user.firstName + " "+$scope.user.lastName +"("+$scope.user.login+")";
        		$scope.userRole = $scope.orgRole.orgRoleName;
        	}
            //for navbar module and menu_items
            if ($scope.isAuthenticated()) {
            	$scope.module2MenuItems = Principal.geModuleMenus();
    		}
            else
            	$scope.module2MenuItems = {};
               
        	return $scope.isAuthenticated();
        }
    });