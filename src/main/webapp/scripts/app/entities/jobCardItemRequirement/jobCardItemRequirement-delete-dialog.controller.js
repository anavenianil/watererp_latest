'use strict';

angular.module('watererpApp')
	.controller('JobCardItemRequirementDeleteController', function($scope, $uibModalInstance, entity, JobCardItemRequirement) {

        $scope.jobCardItemRequirement = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            JobCardItemRequirement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
