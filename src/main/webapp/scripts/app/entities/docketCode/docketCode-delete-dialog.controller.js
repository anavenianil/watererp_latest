'use strict';

angular.module('watererpApp')
	.controller('DocketCodeDeleteController', function($scope, $uibModalInstance, entity, DocketCode) {

        $scope.docketCode = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DocketCode.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
