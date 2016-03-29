'use strict';

angular.module('watererpApp')
	.controller('ComplaintTypeMasterDeleteController', function($scope, $uibModalInstance, entity, ComplaintTypeMaster) {

        $scope.complaintTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ComplaintTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
