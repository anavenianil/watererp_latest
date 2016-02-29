'use strict';

angular.module('watererpApp')
    .controller('CategoryPipeSizeMappingController', function ($scope, $state, CategoryPipeSizeMapping, ParseLinks) {

        $scope.categoryPipeSizeMappings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CategoryPipeSizeMapping.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.categoryPipeSizeMappings.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.categoryPipeSizeMappings = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.categoryPipeSizeMapping = {
                id: null
            };
        };
    });
