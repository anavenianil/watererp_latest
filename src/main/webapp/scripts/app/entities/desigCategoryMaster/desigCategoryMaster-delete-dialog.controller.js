'use strict';

angular.module('watererpApp')
	.controller('DesigCategoryMasterDeleteController', function($scope, $uibModalInstance, entity, DesigCategoryMaster) {

        $scope.desigCategoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DesigCategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
