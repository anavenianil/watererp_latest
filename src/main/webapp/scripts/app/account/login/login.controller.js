'use strict';

angular.module('watererpApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = true;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                if($scope.username=='customer'){
                	$state.go('applicationTxn.new');
                }
                else
                if ($rootScope.previousStateName === 'register') {
                    $state.go('home');
                } else {
                	console.log("Reached here:1")
                	$state.go('home');
//                    $rootScope.back();
                }
            }).catch(function () {
                $scope.authenticationError = true;
            });
        };
    });
