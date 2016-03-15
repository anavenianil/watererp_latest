'use strict';

angular.module('watererpApp')
    .controller('ProceedingsController', function ($scope, $state, Proceedings, ParseLinks) {

        $scope.proceedingss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            Proceedings.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.proceedingss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.proceedingss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.proceedings = {
                waterSupplyConnectionCharges: null,
                waterOtherCharges: null,
                sixtyDaysConsumptionCharges: null,
                waterSupplyImprovementCharges: null,
                meterCost: null,
                greenBrigadeCharges: null,
                rwhsCharges: null,
                totalWaterCharges: null,
                sewerageConnectionCharges: null,
                sewerageOtherCharges: null,
                sewergeImprovementCharges: null,
                totalSewerageCharges: null,
                totalAmount: null,
                totalDeduction: null,
                balance: null,
                id: null
            };
        };
    });
