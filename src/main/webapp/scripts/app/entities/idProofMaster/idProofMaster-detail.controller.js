'use strict';

angular.module('watererpApp')
    .controller('IdProofMasterDetailController', function ($scope, $rootScope, $stateParams, entity, IdProofMaster) {
        $scope.idProofMaster = entity;
        $scope.load = function (id) {
            IdProofMaster.get({id: id}, function(result) {
                $scope.idProofMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:idProofMasterUpdate', function(event, result) {
            $scope.idProofMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
