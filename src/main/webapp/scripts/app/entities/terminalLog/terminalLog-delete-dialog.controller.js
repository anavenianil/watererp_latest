'use strict';

angular.module('watererpApp')
	.controller('TerminalLogDeleteController', function($scope, $uibModalInstance, entity, TerminalLog) {

        $scope.terminalLog = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TerminalLog.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
