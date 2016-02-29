'use strict';

angular.module('watererpApp')
    .controller('ReqOrgWorkflowMappingController', function ($scope, $state, ReqOrgWorkflowMapping, ParseLinks) {

        $scope.reqOrgWorkflowMappings = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ReqOrgWorkflowMapping.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.reqOrgWorkflowMappings.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.reqOrgWorkflowMappings = [];
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
            $scope.reqOrgWorkflowMapping = {
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
