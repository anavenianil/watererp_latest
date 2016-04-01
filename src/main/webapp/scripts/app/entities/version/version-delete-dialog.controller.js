'use strict';

angular.module('watererpApp')
	.controller('VersionDeleteController', function($scope, $uibModalInstance, entity, Version) {

        $scope.version = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Version.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
