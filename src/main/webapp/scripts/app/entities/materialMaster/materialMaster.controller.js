'use strict';

angular.module('watererpApp')
    .controller('MaterialMasterController', function ($scope, $state, MaterialMaster, ParseLinks) {

        $scope.materialMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            MaterialMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.materialMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.materialMasters = [];
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
            $scope.materialMaster = {
                materialName: null,
                consumableFlag: null,
                uomId: null,
                categoryId: null,
                subCategoryId: null,
                itemCodeId: null,
                itemSubCodeId: null,
                rateContractFlag: null,
                unitRate: null,
                description: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                companyCodeId: null,
                id: null
            };
        };
    });
