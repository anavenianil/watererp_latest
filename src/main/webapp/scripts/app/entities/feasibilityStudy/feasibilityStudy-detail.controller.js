'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyDetailController', function ($scope, $rootScope, $stateParams, entity, FeasibilityStudy, DivisionMaster, ZoneMaster, StreetMaster, ApplicationTxn, User, CategoryMaster) {
        $scope.feasibilityStudy = entity;
        $scope.load = function (id) {
            FeasibilityStudy.get({id: id}, function(result) {
                $scope.feasibilityStudy = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:feasibilityStudyUpdate', function(event, result) {
            $scope.feasibilityStudy = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
