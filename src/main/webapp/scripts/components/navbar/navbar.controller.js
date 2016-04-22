'use strict';

angular
		.module('watererpApp')
		.controller(
				'NavbarController',
				function($rootScope, $scope, $http, $location, $state, Auth,
						Principal) {
					$scope.isAuthenticated = Principal.isAuthenticated;
					$scope.$state = $state;
					$scope.module2MenuItems = {};
					$scope.module = {};
					$scope.orgRole = {};
					//$scope.inProduction = ENV === 'prod';

					$scope.getCurrentModule = function() {
						var path = $location.absUrl()
						var moduleName = "";
						console.log("This is the path:" + path)

						//if path="http://localhost:8080/billingAndCollectionMain#/"
						//below will give "billingAndCollectionMain#"
						var parts = path.split("/");
						if (parts[parts.length - 1].length == 0) {
							moduleName = parts[parts.length - 2];
						} else {
							moduleName = parts[parts.length - 1];
						}
						//console.log(moduleName); //"billingAndCollectionMain#"

						if (moduleName.charAt(moduleName.length - 1) == '#') {
							moduleName = moduleName.slice(0, -1);
							var matches = Principal.geModuleMenus().filter(function(val, index, array) {
							    return val.server_url === moduleName;
							});

							console.log(matches);
							
							$scope.module = matches[0];
						} else {
							moduleName = "";
							$scope.module = {};
						}

					}

					$scope.getByKey = function(key) {
						var found = null;

						for (var i = 0; i < data.length; i++) {
							var element = data[i];

							if (element.Key == key) {
								found = element;
								break;
							}
						}

						return found;
					}

					$scope.logout = function() {
						Auth.logout();
						window.location = '/';
						$scope.userRole = "";
					};

					$scope.getLogin = function() {
						$scope.user = Principal.getLogonUser();
						$scope.orgRole = Principal.getOrgRole();
						//console.log("User is: "+JSON.stringify($scope.user));
						if ($scope.user == null) {
							$scope.navbarUserId = "";
							$scope.userRole = "";
						} else {
							$scope.navbarUserId = $scope.user.firstName + " "
									+ $scope.user.lastName + "("
									+ $scope.user.login + ")";
							$scope.userRole = $scope.orgRole.orgRoleName;
						}
						//for navbar module and menu_items
						if ($scope.isAuthenticated()) {
							$scope.module2MenuItems = Principal.geModuleMenus();
						} else
							$scope.module2MenuItems = {};

						return $scope.isAuthenticated();
					}

					$scope.getCurrentModule();

				});