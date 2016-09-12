'use strict';

angular.module('watererpApp')
    .controller('HydrantComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, HydrantComplaint, WaterLeakageComplaint) {
        $scope.hydrantComplaint = entity;
        $scope.load = function (id) {
            HydrantComplaint.get({id: id}, function(result) {
                $scope.hydrantComplaint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:hydrantComplaintUpdate', function(event, result) {
            $scope.hydrantComplaint = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
