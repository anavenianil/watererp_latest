'use strict';

angular.module('watererpApp')
    .controller('OrgHierarchyController', function ($scope, $state, OrgHierarchy, ParseLinks) {

        $scope.orgHierarchys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OrgHierarchy.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.orgHierarchys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.orgHierarchys = [];
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
            $scope.orgHierarchy = {
                hierarchyName: null,
                parentHierarchyId: null,
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
