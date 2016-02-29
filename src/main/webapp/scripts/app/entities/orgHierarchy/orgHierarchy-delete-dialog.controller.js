'use strict';

angular.module('watererpApp')
	.controller('OrgHierarchyDeleteController', function($scope, $uibModalInstance, entity, OrgHierarchy) {

        $scope.orgHierarchy = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OrgHierarchy.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
