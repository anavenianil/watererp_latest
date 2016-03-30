'use strict';

angular.module('watererpApp')
    .controller('ItemCompanyMasterController', function ($scope, $state, ItemCompanyMaster, ParseLinks) {

        $scope.itemCompanyMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ItemCompanyMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemCompanyMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.itemCompanyMasters = [];
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
            $scope.itemCompanyMaster = {
                name: null,
                description: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                companyCode: null,
                id: null
            };
        };
    });
