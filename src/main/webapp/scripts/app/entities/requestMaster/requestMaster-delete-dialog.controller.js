'use strict';

angular.module('watererpApp')
	.controller('RequestMasterDeleteController', function($scope, $uibModalInstance, entity, RequestMaster) {

        $scope.requestMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RequestMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
