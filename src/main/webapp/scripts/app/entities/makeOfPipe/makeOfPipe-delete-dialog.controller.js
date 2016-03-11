'use strict';

angular.module('watererpApp')
	.controller('MakeOfPipeDeleteController', function($scope, $uibModalInstance, entity, MakeOfPipe) {

        $scope.makeOfPipe = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MakeOfPipe.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
