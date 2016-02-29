'use strict';

angular.module('watererpApp')
    .controller('EmpRoleMappingController', function ($scope, $state, EmpRoleMapping, ParseLinks) {

        $scope.empRoleMappings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            EmpRoleMapping.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.empRoleMappings.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.empRoleMappings = [];
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
            $scope.empRoleMapping = {
                internalDivision: null,
                internalRole: null,
                creationDate: null,
                lastModifiedDate: null,
                parentRoleId: null,
                id: null
            };
        };
    });
