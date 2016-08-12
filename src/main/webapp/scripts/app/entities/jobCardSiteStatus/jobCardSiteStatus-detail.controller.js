'use strict';

angular.module('watererpApp')
    .controller('JobCardSiteStatusDetailController', function ($scope, $rootScope, $stateParams, entity, JobCardSiteStatus, WaterLeakageComplaint) {
        $scope.jobCardSiteStatus = entity;
        $scope.load = function (id) {
            JobCardSiteStatus.get({id: id}, function(result) {
                $scope.jobCardSiteStatus = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:jobCardSiteStatusUpdate', function(event, result) {
            $scope.jobCardSiteStatus = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
