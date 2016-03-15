'use strict';

angular.module('watererpApp')
	.controller('ProceedingsDeleteController', function($scope, $uibModalInstance, entity, Proceedings) {

        $scope.proceedings = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Proceedings.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
