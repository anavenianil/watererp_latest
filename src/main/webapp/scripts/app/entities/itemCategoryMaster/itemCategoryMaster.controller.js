'use strict';

angular.module('watererpApp')
    .controller('ItemCategoryMasterController', function ($scope, $state, ItemCategoryMaster, ParseLinks) {

        $scope.itemCategoryMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ItemCategoryMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemCategoryMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.itemCategoryMasters = [];
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
            $scope.itemCategoryMaster = {
                name: null,
                description: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                categoryCode: null,
                id: null
            };
        };
    });
