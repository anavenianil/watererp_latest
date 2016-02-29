'use strict';

angular.module('watererpApp')
    .controller('DesignationMasterController', function ($scope, $state, DesignationMaster, ParseLinks) {

        $scope.designationMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            DesignationMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.designationMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.designationMasters = [];
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
            $scope.designationMaster = {
                name: null,
                creationDate: null,
                lastModifiedDate: null,
                description: null,
                orderNo: null,
                serviceType: null,
                code: null,
                desigalias: null,
                id: null
            };
        };
    });
