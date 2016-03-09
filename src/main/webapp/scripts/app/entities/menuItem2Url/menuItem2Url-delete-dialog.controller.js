'use strict';

angular.module('watererpApp')
	.controller('MenuItem2UrlDeleteController', function($scope, $uibModalInstance, entity, MenuItem2Url) {

        $scope.menuItem2Url = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MenuItem2Url.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
