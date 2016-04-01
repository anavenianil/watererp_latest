'use strict';

angular.module('watererpApp')
	.controller('CurrentUsersDeleteController', function($scope, $uibModalInstance, entity, CurrentUsers) {

        $scope.currentUsers = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CurrentUsers.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
