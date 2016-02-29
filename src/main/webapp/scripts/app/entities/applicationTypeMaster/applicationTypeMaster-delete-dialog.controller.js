'use strict';

angular.module('watererpApp')
	.controller('ApplicationTypeMasterDeleteController', function($scope, $uibModalInstance, entity, ApplicationTypeMaster) {

        $scope.applicationTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ApplicationTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
