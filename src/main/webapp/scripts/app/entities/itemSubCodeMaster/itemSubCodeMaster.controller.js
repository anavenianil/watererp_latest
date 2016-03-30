'use strict';

angular.module('watererpApp')
    .controller('ItemSubCodeMasterController', function ($scope, $state, ItemSubCodeMaster, ParseLinks) {

        $scope.itemSubCodeMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ItemSubCodeMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemSubCodeMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.itemSubCodeMasters = [];
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
            $scope.itemSubCodeMaster = {
                itemCodeId: null,
                itemSubCode: null,
                itemName: null,
                description: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                itemCcodeId: null,
                itemCategoryId: null,
                itemSubCategoryID: null,
                id: null
            };
        };
    });
