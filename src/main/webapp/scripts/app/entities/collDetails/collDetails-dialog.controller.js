'use strict';

angular.module('watererpApp')
    .controller('CollDetailsDialogController', function ($scope, $state, CollDetails, ParseLinks, $stateParams) {

        $scope.collDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CollDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.collDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.collDetailss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();
        
        
        $scope.collDetailsId = $stateParams.id;
        $scope.collDetails = {};
        if($stateParams.id != null){
        	CollDetails.get({id : $scope.collDetailsId}, function(result) {
                $scope.collDetails = result;
            });
        }
        
        
        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:collDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('collDetails');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.collDetails.id != null) {
                CollDetails.update($scope.collDetails, onSaveSuccess, onSaveError);
            } else {
                CollDetails.save($scope.collDetails, onSaveSuccess, onSaveError);
            }
        };
        
        
        
        $scope.datePickerForReceiptDt = {};

        $scope.datePickerForReceiptDt.status = {
            opened: false
        };

        $scope.datePickerForReceiptDtOpen = function($event) {
            $scope.datePickerForReceiptDt.status.opened = true;
        };
        $scope.datePickerForInstrDt = {};

        $scope.datePickerForInstrDt.status = {
            opened: false
        };

        $scope.datePickerForInstrDtOpen = function($event) {
            $scope.datePickerForInstrDt.status.opened = true;
        };
        $scope.datePickerForCollTime = {};

        $scope.datePickerForCollTime.status = {
            opened: false
        };

        $scope.datePickerForCollTimeOpen = function($event) {
            $scope.datePickerForCollTime.status.opened = true;
        };
        
        
        
        
        


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.collDetails = {
                reversalRef: null,
                receiptNo: null,
                receiptAmt: null,
                receiptDt: null,
                receiptTime: null,
                receiptMode: null,
                instrNo: null,
                instrDt: null,
                instrIssuer: null,
                svrStatus: null,
                can: null,
                consName: null,
                terminalId: null,
                collTime: null,
                txnStatus: null,
                meterReaderId: null,
                userId: null,
                remarks: null,
                settlementId: null,
                extSettlementId: null,
                lat: null,
                longI: null,
                id: null
            };
        };
    });







































/*'use strict';

angular.module('watererpApp').controller('CollDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CollDetails',
        function($scope, $stateParams, $uibModalInstance, entity, CollDetails) {

        $scope.collDetails = entity;
        $scope.load = function(id) {
            CollDetails.get({id : id}, function(result) {
                $scope.collDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:collDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.collDetails.id != null) {
                CollDetails.update($scope.collDetails, onSaveSuccess, onSaveError);
            } else {
                CollDetails.save($scope.collDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForReceiptDt = {};

        $scope.datePickerForReceiptDt.status = {
            opened: false
        };

        $scope.datePickerForReceiptDtOpen = function($event) {
            $scope.datePickerForReceiptDt.status.opened = true;
        };
        $scope.datePickerForInstrDt = {};

        $scope.datePickerForInstrDt.status = {
            opened: false
        };

        $scope.datePickerForInstrDtOpen = function($event) {
            $scope.datePickerForInstrDt.status.opened = true;
        };
        $scope.datePickerForCollTime = {};

        $scope.datePickerForCollTime.status = {
            opened: false
        };

        $scope.datePickerForCollTimeOpen = function($event) {
            $scope.datePickerForCollTime.status.opened = true;
        };
}]);
*/