'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyController', function ($scope, $state, FeasibilityStudy) {

        $scope.feasibilityStudys = [];
        $scope.loadAll = function() {
            FeasibilityStudy.query(function(result) {
               $scope.feasibilityStudys = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.feasibilityStudy = {
                createdDate: null,
                modifiedDate: null,
                preparedDate: null,
                zonalHeadApprovalDate: null,
                deptHeadInspectedDate: null,
                operationMangrapproveDate: null,
                status: null,
                id: null
            };
        };
    });
