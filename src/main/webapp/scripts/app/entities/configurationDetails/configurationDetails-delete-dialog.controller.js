'use strict';

angular.module('watererpApp')
	.controller('ConfigurationDetailsDeleteController', function($scope, $uibModalInstance, entity, ConfigurationDetails) {

        $scope.configurationDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ConfigurationDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
