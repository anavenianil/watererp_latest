'use strict';

angular.module('watererpApp')
    .controller('MeterChangeDetailController', function ($scope, $rootScope, $stateParams, entity, MeterChange, CustDetails, MeterDetails, User,
    		ApplicationTxnService) {
        $scope.meterChange = entity;
        $scope.load = function (id) {
            MeterChange.get({id: id}, function(result) {
                $scope.meterChange = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:meterChangeUpdate', function(event, result) {
            $scope.meterChange = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        
        $scope.approvalMeterChange = function(id, remarks){
        	ApplicationTxnService.approveMeterChange(id, remarks);
        	$state.go('meterChange');
        }

    });
