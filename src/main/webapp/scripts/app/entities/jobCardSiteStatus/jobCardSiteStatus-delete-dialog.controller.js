'use strict';

angular.module('watererpApp')
	.controller('JobCardSiteStatusDeleteController', function($scope, $uibModalInstance, entity, JobCardSiteStatus) {

        $scope.jobCardSiteStatus = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            JobCardSiteStatus.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
