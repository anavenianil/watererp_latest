'use strict';

angular.module('watererpApp')
    .controller('BurstComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, BurstComplaint, WaterLeakageComplaint) {
        $scope.burstComplaint = entity;
        $scope.load = function (id) {
            BurstComplaint.get({id: id}, function(result) {
                $scope.burstComplaint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:burstComplaintUpdate', function(event, result) {
            $scope.burstComplaint = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
