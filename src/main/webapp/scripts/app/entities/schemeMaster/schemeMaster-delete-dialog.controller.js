'use strict';

angular.module('watererpApp')
	.controller('SchemeMasterDeleteController', function($scope, $uibModalInstance, entity, SchemeMaster) {

        $scope.schemeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SchemeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
