'use strict';

angular.module('watererpApp')
	.controller('TerminalDeleteController', function($scope, $uibModalInstance, entity, Terminal) {

        $scope.terminal = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Terminal.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
