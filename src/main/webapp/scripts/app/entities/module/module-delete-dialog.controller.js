'use strict';

angular.module('watererpApp')
	.controller('ModuleDeleteController', function($scope, $uibModalInstance, entity, Module) {

        $scope.module = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Module.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
