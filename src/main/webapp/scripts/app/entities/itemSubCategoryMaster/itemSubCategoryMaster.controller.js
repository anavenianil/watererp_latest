'use strict';

angular.module('watererpApp')
    .controller('ItemSubCategoryMasterController', function ($scope, $state, ItemSubCategoryMaster, ParseLinks) {

        $scope.itemSubCategoryMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ItemSubCategoryMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemSubCategoryMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.itemSubCategoryMasters = [];
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
            $scope.itemSubCategoryMaster = {
                itemSubCategoryCode: null,
                description: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                name: null,
                categoryCode: null,
                id: null
            };
        };
    });
