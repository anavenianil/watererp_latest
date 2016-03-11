'use strict';

angular.module('watererpApp')
	.controller('FeasibilityStudyDeleteController', function($scope, $uibModalInstance, entity, FeasibilityStudy) {

        $scope.feasibilityStudy = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            FeasibilityStudy.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
