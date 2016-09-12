'use strict';

angular.module('watererpApp')
    .controller('ValveComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, ValveComplaint, WaterLeakageComplaint) {
        $scope.valveComplaint = entity;
        $scope.load = function (id) {
            ValveComplaint.get({id: id}, function(result) {
                $scope.valveComplaint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:valveComplaintUpdate', function(event, result) {
            $scope.valveComplaint = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
