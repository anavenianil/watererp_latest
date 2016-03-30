'use strict';

angular.module('watererpApp')
	.controller('SibEntryDeleteController', function($scope, $uibModalInstance, entity, SibEntry) {

        $scope.sibEntry = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SibEntry.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
