'use strict';

angular.module('watererpApp')
    .controller('UomController', function ($scope, $state, Uom) {

        $scope.uoms = [];
        $scope.loadAll = function() {
            Uom.query(function(result) {
               $scope.uoms = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.uom = {
                value: null,
                id: null
            };
        };
    });
