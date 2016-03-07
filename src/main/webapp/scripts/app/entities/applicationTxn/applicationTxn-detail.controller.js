'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDetailController', function ($scope, $rootScope, $stateParams, entity, ApplicationTxn, ApplicationTypeMaster, ConnectionTypeMaster, CtegoryMaster, PipeSizeMaster, SewerSize, FileNumber, Customer) {
        $scope.applicationTxn = entity;
        $scope.load = function (id) {
        	$('#viewApplicationTxnModal').modal('hide');
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:applicationTxnUpdate', function(event, result) {
            $scope.applicationTxn = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
