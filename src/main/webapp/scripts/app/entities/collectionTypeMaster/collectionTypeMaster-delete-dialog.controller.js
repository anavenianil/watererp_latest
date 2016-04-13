'use strict';

angular.module('watererpApp')
	.controller('CollectionTypeMasterDeleteController', function($scope, $uibModalInstance, entity, CollectionTypeMaster) {

        $scope.collectionTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CollectionTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
