'use strict';

angular.module('waterERPApp')
	.controller('CustDetailsDeleteController', function($scope, $uibModalInstance, entity, CustDetails) {

        $scope.custDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CustDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
