'use strict';

angular.module('watererpApp')
    .controller('WorkflowMasterController', function ($scope, $state, WorkflowMaster, ParseLinks) {

        $scope.workflowMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            WorkflowMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.workflowMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.workflowMasters = [];
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
            $scope.workflowMaster = {
                workflowName: null,
                toWorkflow: null,
                creationDate: null,
                lastModifiedDate: null,
                id: null
            };
        };
    });
