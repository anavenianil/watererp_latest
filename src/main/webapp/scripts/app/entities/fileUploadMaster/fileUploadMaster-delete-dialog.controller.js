'use strict';

angular.module('watererpApp')
	.controller('FileUploadMasterDeleteController', function($scope, $uibModalInstance, entity, FileUploadMaster) {

        $scope.fileUploadMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            FileUploadMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
