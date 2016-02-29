'use strict';

angular.module('watererpApp')
    .controller('ApplicationTypeMasterController', function ($scope, $state, ApplicationTypeMaster, ParseLinks) {

        $scope.applicationTypeMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ApplicationTypeMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTypeMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.applicationTypeMasters = [];
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
            $scope.applicationTypeMaster = {
                applicationType: null,
                createdDate: null,
                updatedDate: null,
                status: null,
                createdBy: null,
                updatedBy: null,
                id: null
            };
        };
    });
