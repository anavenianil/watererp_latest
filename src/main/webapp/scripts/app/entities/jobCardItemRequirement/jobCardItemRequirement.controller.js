'use strict';

angular.module('watererpApp')
    .controller('JobCardItemRequirementController', function ($scope, $state, JobCardItemRequirement, ParseLinks) {

        $scope.jobCardItemRequirements = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            JobCardItemRequirement.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.jobCardItemRequirements.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.jobCardItemRequirements = [];
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
            $scope.jobCardItemRequirement = {
                quantity: null,
                replaceLength: null,
                cascadeClamp: null,
                noOfSection: null,
                noOfClamps: null,
                type: null,
                domainObject: null,
                id: null
            };
        };
    });
