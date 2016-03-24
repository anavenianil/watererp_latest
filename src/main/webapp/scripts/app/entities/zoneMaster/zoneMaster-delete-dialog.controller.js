'use strict';

angular.module('watererpApp')
	.controller('ZoneMasterDeleteController', function($scope, $uibModalInstance, entity, ZoneMaster) {

        $scope.zoneMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ZoneMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
