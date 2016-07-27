'use strict';

angular.module('watererpApp')
    .controller('IdProofMasterController', function ($scope, $state, IdProofMaster) {

        $scope.idProofMasters = [];
        $scope.loadAll = function() {
            IdProofMaster.query(function(result) {
               $scope.idProofMasters = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.idProofMaster = {
                idProof: null,
                id: null
            };
        };
    });
