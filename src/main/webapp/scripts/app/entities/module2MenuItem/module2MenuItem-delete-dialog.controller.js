'use strict';

angular.module('watererpApp')
	.controller('Module2MenuItemDeleteController', function($scope, $uibModalInstance, entity, Module2MenuItem) {

        $scope.module2MenuItem = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Module2MenuItem.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
