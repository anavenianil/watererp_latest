'use strict';

angular.module('watererpApp')
    .controller('RoleWorkflowMappingController', function ($scope, $state, RoleWorkflowMapping, ParseLinks) {

        $scope.roleWorkflowMappings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            RoleWorkflowMapping.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.roleWorkflowMappings.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.roleWorkflowMappings = [];
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
            $scope.roleWorkflowMapping = {
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
