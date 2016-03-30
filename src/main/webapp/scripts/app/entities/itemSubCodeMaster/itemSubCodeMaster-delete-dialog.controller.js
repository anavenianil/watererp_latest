'use strict';

angular.module('watererpApp')
	.controller('ItemSubCodeMasterDeleteController', function($scope, $uibModalInstance, entity, ItemSubCodeMaster) {

        $scope.itemSubCodeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemSubCodeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
