'use strict';

angular
		.module('watererpApp')
		.controller(
				'NavbarController',
				function($rootScope, $scope, $http, $location, $state, Auth,
						Principal, Navbar) {
					$scope.isAuthenticated = Principal.isAuthenticated;
					$scope.$state = $state;
					$scope.module2MenuItems = {};
					$scope.module = {};
					$scope.orgRole = {};
					// $scope.inProduction = ENV === 'prod';

					if ($scope.isAuthenticated()) {
						Principal.getModuleMenus().then(function(response) {
							$scope.module2MenuItems = response;

							$scope.module = Navbar.getCurrentModule(response);
						});
						

						console.log("GetOrgRole called.");
						Principal.getOrgRole().then(function(response) {
							console.log("GetOrgRole Reponse received:" + JSON.stringify(response));
							$scope.orgRole = response;
						});
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
						window.location = '/erpLogin.html';
						$scope.userRole = "";
					};

					$scope.getLogin = function() {
						$scope.user = Principal.getLogonUser();

						// console.log("User is: "+JSON.stringify($scope.user));
						if ($scope.user == null) {
							$scope.navbarUserId = "";
							$scope.userRole = "";
						} else {
							$scope.navbarUserId = $scope.user.firstName + " "
									+ $scope.user.lastName + "("
									+ $scope.user.login + ")";
							$scope.userRole = $scope.orgRole.orgRoleName;
						}
						// for navbar module and menu_items


						return $scope.isAuthenticated();
					}

				});