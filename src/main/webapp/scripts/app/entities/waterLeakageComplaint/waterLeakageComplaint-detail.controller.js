'use strict';

angular.module('watererpApp')
    .controller('WaterLeakageComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, WaterLeakageComplaint, DivisionMaster, StreetMaster, JobCardItemRequirement) {
        $scope.waterLeakageComplaint = entity;
        $scope.load = function (id) {
            WaterLeakageComplaint.get({id: id}, function(result) {
                $scope.waterLeakageComplaint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:waterLeakageComplaintUpdate', function(event, result) {
            $scope.waterLeakageComplaint = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
