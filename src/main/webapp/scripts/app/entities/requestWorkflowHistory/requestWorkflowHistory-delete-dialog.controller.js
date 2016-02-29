'use strict';

angular.module('watererpApp')
	.controller('RequestWorkflowHistoryDeleteController', function($scope, $uibModalInstance, entity, RequestWorkflowHistory) {

        $scope.requestWorkflowHistory = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RequestWorkflowHistory.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
