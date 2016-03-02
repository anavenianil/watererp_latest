'use strict';

angular.module('watererpApp')
	.controller('SewerSizeDeleteController', function($scope, $uibModalInstance, entity, SewerSize) {

        $scope.sewerSize = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SewerSize.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
