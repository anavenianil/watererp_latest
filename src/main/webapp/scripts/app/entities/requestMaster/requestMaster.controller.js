'use strict';

angular.module('watererpApp')
    .controller('RequestMasterController', function ($scope, $state, RequestMaster, ParseLinks) {

        $scope.requestMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            RequestMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.requestMasters = [];
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
            $scope.requestMaster = {
                requestType: null,
                creationDate: null,
                lastModifiedDate: null,
                description: null,
                internalFlag: null,
                id: null
            };
        };
    });
