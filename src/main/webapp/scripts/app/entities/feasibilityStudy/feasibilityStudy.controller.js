'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyController', function ($scope, $state, FeasibilityStudy, ParseLinks) {

        $scope.feasibilityStudys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            FeasibilityStudy.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.feasibilityStudys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.feasibilityStudys = [];
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
            $scope.feasibilityStudy = {
                plotAreaInSqMtrs: null,
                plotAreaInYards: null,
                noOfFlatsOrNoOfUnits: null,
                noOfFloors: null,
                totalPlinthArea: null,
                waterRequirement: null,
                id: null
            };
        };
    });
