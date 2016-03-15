'use strict';

angular.module('watererpApp')
    .controller('ProceedingsDialogController', function ($scope, $state, Proceedings, ParseLinks, ApplicationTxn) {

        $scope.proceedingss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        //$scope.proceedings = entity;
        $scope.proceedings = {};
        
        /**
         * to find customer details by file no.
         */
        $scope.getCustomer = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                $scope.feasibilityStudy.applicationTxn = $scope.applicationTxn;
                $scope.feasibilityStudy.applicationTxn.id = $scope.applicationTxn.id;
            });	
        }
        
        
        /**
         * for saving Proceedings
         */
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:proceedingsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };
        
        
        
        
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
        //$scope.loadAll();


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






/*'use strict';

angular.module('watererpApp').controller('ProceedingsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proceedings', 'ApplicationTxn',
        function($scope, $stateParams, $uibModalInstance, entity, Proceedings, ApplicationTxn) {

        $scope.proceedings = entity;
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.load = function(id) {
            Proceedings.get({id : id}, function(result) {
                $scope.proceedings = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:proceedingsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
*/