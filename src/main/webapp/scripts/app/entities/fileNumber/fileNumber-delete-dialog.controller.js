'use strict';

angular.module('watererpApp')
	.controller('FileNumberDeleteController', function($scope, $uibModalInstance, entity, FileNumber) {

        $scope.fileNumber = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            FileNumber.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
