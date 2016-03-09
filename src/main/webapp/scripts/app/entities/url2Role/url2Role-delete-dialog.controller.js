'use strict';

angular.module('watererpApp')
	.controller('Url2RoleDeleteController', function($scope, $uibModalInstance, entity, Url2Role) {

        $scope.url2Role = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Url2Role.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
