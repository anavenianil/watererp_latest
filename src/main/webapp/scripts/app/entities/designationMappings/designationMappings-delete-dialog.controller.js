'use strict';

angular.module('watererpApp')
	.controller('DesignationMappingsDeleteController', function($scope, $uibModalInstance, entity, DesignationMappings) {

        $scope.designationMappings = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DesignationMappings.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
