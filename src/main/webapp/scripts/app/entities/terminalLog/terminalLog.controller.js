'use strict';

angular.module('watererpApp')
    .controller('TerminalLogController', function ($scope, $state, TerminalLog, ParseLinks) {

        $scope.terminalLogs = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            TerminalLog.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.terminalLogs.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.terminalLogs = [];
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
            $scope.terminalLog = {
                amount: null,
                lastModified: null,
                modifiedBy: null,
                userId: null,
                bankDepositDate: null,
                beforeUpdate: null,
                afterUpdate: null,
                mrCode: null,
                remark: null,
                txnType: null,
                id: null
            };
        };
    });
