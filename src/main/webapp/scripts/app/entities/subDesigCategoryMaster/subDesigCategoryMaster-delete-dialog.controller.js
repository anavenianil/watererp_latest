'use strict';

angular.module('watererpApp')
	.controller('SubDesigCategoryMasterDeleteController', function($scope, $uibModalInstance, entity, SubDesigCategoryMaster) {

        $scope.subDesigCategoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SubDesigCategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
