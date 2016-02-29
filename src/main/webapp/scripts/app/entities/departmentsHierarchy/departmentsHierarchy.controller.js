'use strict';

angular.module('watererpApp')
    .controller('DepartmentsHierarchyController', function ($scope, $state, DepartmentsHierarchy, ParseLinks) {

        $scope.departmentsHierarchys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            DepartmentsHierarchy.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.departmentsHierarchys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.departmentsHierarchys = [];
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
            $scope.departmentsHierarchy = {
                deptHierarchyName: null,
                parentDeptHierarchyId: null,
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
