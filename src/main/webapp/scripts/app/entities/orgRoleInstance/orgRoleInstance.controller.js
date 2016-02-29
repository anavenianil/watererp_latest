'use strict';

angular.module('watererpApp')
    .controller('OrgRoleInstanceController', function ($scope, $state, OrgRoleInstance, ParseLinks) {

        $scope.orgRoleInstances = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OrgRoleInstance.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.orgRoleInstances.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.orgRoleInstances = [];
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
            $scope.orgRoleInstance = {
                orgRoleName: null,
                parentOrgRoleId: null,
                creationDate: null,
                lastModifiedDate: null,
                isHead: null,
                id: null
            };
        };
    });
