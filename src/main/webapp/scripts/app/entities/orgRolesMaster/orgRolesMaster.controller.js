'use strict';

angular.module('watererpApp')
    .controller('OrgRolesMasterController', function ($scope, $state, OrgRolesMaster, ParseLinks) {

        $scope.orgRolesMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OrgRolesMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.orgRolesMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.orgRolesMasters = [];
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
            $scope.orgRolesMaster = {
                orgRoleName: null,
                hierarchyId: null,
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
