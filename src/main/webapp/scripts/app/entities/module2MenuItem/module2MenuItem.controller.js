'use strict';

angular.module('watererpApp')
    .controller('Module2MenuItemController', function ($scope, $state, Module2MenuItem, ParseLinks, Module) {

        $scope.module2MenuItems = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.modules = Module.query();
        $scope.loadAll = function() {
            Module2MenuItem.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.module2MenuItems.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.module2MenuItems = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            //$scope.loadAll();
        };
        //$scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.module2MenuItem = {
                priority: null,
                id: null
            };
        };
        
        $scope.getModuleMenu = function(moduleId){
        	$scope.module2MenuItems = Module2MenuItem.getByModuleId({moduleId:moduleId});
        }
    });
