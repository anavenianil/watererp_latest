'use strict';

angular.module('watererpApp')
    .controller('TerminalController', function ($scope, $state, Terminal, ParseLinks) {

        $scope.terminals = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            Terminal.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.terminals.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.terminals = [];
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
            $scope.terminal = {
                amount: null,
                status: null,
                userId: null,
                mrCode: null,
                secCode: null,
                divCode: null,
                secName: null,
                userName: null,
                mobileNo: null,
                ver: null,
                id: null
            };
        };
    });
