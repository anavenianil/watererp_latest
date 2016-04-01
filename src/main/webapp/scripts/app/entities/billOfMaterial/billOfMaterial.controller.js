'use strict';

angular.module('watererpApp')
    .controller('BillOfMaterialController', function ($scope, $state, BillOfMaterial) {

        $scope.billOfMaterials = [];
        $scope.loadAll = function() {
            BillOfMaterial.query(function(result) {
               $scope.billOfMaterials = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.billOfMaterial = {
                amount: null,
                bankName: null,
                branchName: null,
                checkDate: null,
                id: null
            };
        };
    });
