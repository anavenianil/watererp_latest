'use strict';

angular.module('watererpApp')
	.controller('StreetMasterDeleteController', function($scope, $uibModalInstance, entity, StreetMaster) {

        $scope.streetMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            StreetMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
