'use strict';

angular.module('watererpApp')
    .controller('DesigCategoryMasterController', function ($scope, $state, DesigCategoryMaster, ParseLinks) {

        $scope.desigCategoryMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            DesigCategoryMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.desigCategoryMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.desigCategoryMasters = [];
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
            $scope.desigCategoryMaster = {
                name: null,
                creationDate: null,
                lastModifiedDate: null,
                description: null,
                alias: null,
                orderBy: null,
                id: null
            };
        };
    });
