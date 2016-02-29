'use strict';

angular.module('watererpApp')
    .controller('RequestWorkflowHistoryController', function ($scope, $state, RequestWorkflowHistory, ParseLinks) {

        $scope.requestWorkflowHistorys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            RequestWorkflowHistory.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.requestWorkflowHistorys = [];
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
            $scope.requestWorkflowHistory = {
                requestStage: null,
                assignedDate: null,
                actionedDate: null,
                remarks: null,
                ipAddress: null,
                assignedRole: null,
                domainObject: null,
                id: null
            };
        };
    });
