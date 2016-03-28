'use strict';

angular.module('watererpApp')
    .controller('CurrentUsersController', function ($scope, $state, CurrentUsers, ParseLinks) {

        $scope.currentUserss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CurrentUsers.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.currentUserss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.currentUserss = [];
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
            $scope.currentUsers = {
                terminalId: null,
                meterReaderId: null,
                userId: null,
                requestType: null,
                loginTime: null,
                ip: null,
                id: null
            };
        };
    });
