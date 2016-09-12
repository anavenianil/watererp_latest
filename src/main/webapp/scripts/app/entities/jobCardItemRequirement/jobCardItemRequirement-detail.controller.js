'use strict';

angular.module('watererpApp')
    .controller('JobCardItemRequirementDetailController', function ($scope, $rootScope, $stateParams, entity, JobCardItemRequirement, MaterialMaster, Uom, WaterLeakageComplaint) {
        $scope.jobCardItemRequirement = entity;
        $scope.load = function (id) {
            JobCardItemRequirement.get({id: id}, function(result) {
                $scope.jobCardItemRequirement = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:jobCardItemRequirementUpdate', function(event, result) {
            $scope.jobCardItemRequirement = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
