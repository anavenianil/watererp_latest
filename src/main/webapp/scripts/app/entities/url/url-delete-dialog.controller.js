'use strict';

angular.module('watererpApp')
	.controller('UrlDeleteController', function($scope, $uibModalInstance, entity, Url) {

        $scope.url = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Url.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
