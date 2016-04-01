'use strict';

angular.module('watererpApp')
    .controller('BillFullDetailsDialogController', function ($scope, $state, BillFullDetails, ParseLinks, $stateParams) {

        $scope.billFullDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            BillFullDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.billFullDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.billFullDetailss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();        
        
        $scope.billFullDetailsId = $stateParams.id;
        $scope.billFullDetails = {};
        if($stateParams.id != null){        
        BillFullDetails.get({id : $scope.billFullDetailsId}, function(result) {
            $scope.billFullDetails = result;
        });
    	}
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billFullDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('billFullDetails');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billFullDetails.id != null) {
                BillFullDetails.update($scope.billFullDetails, onSaveSuccess, onSaveError);
            } else {
                BillFullDetails.save($scope.billFullDetails, onSaveSuccess, onSaveError);
            }
        };
        
        $scope.datePickerForConnDate = {};

        $scope.datePickerForConnDate.status = {
            opened: false
        };

        $scope.datePickerForConnDateOpen = function($event) {
            $scope.datePickerForConnDate.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
        $scope.datePickerForLastPymtDt = {};

        $scope.datePickerForLastPymtDt.status = {
            opened: false
        };

        $scope.datePickerForLastPymtDtOpen = function($event) {
            $scope.datePickerForLastPymtDt.status.opened = true;
        };
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.billFullDetails = {
                can: null,
                divcode: null,
                seccode: null,
                secname: null,
                metreadercode: null,
                connDate: null,
                consName: null,
                houseNo: null,
                address: null,
                city: null,
                pinCode: null,
                category: null,
                pipeSize: null,
                boardMeter: null,
                sewerage: null,
                meterNo: null,
                prevBillType: null,
                prevBillMonth: null,
                prevAvgKl: null,
                metReadingDt: null,
                prevReading: null,
                metReadingMo: null,
                metAvgKl: null,
                arrears: null,
                reversalAmt: null,
                installment: null,
                otherCharges: null,
                surCharge: null,
                hrsSurCharge: null,
                resUnits: null,
                metCostInstallment: null,
                intOnArrears: null,
                lastPymtDt: null,
                lastPymtAmt: null,
                billNumber: null,
                billDate: null,
                billTime: null,
                meterMake: null,
                currentBillType: null,
                fromMonth: null,
                toMonth: null,
                meterFixDate: null,
                initialReading: null,
                presentReading: null,
                units: null,
                waterCess: null,
                sewerageCess: null,
                serviceCharge: null,
                meterServiceCharge: null,
                totalAmount: null,
                netPayableAmount: null,
                telephoneNo: null,
                meterStatus: null,
                mcMetReaderCode: null,
                billFlag: null,
                svrStatus: null,
                terminalId: null,
                meterReaderId: null,
                userId: null,
                mobileNo: null,
                noticeNo: null,
                lat: null,
                longI: null,
                nometerAmt: null,
                id: null
            };
        };
        
        
        
        
        
        
        
    });









































/*'use strict';

angular.module('watererpApp').controller('BillFullDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillFullDetails',
        function($scope, $stateParams, $uibModalInstance, entity, BillFullDetails) {

        $scope.billFullDetails = entity;
        $scope.load = function(id) {
            BillFullDetails.get({id : id}, function(result) {
                $scope.billFullDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billFullDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billFullDetails.id != null) {
                BillFullDetails.update($scope.billFullDetails, onSaveSuccess, onSaveError);
            } else {
                BillFullDetails.save($scope.billFullDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForConnDate = {};

        $scope.datePickerForConnDate.status = {
            opened: false
        };

        $scope.datePickerForConnDateOpen = function($event) {
            $scope.datePickerForConnDate.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
        $scope.datePickerForLastPymtDt = {};

        $scope.datePickerForLastPymtDt.status = {
            opened: false
        };

        $scope.datePickerForLastPymtDtOpen = function($event) {
            $scope.datePickerForLastPymtDt.status.opened = true;
        };
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };
}]);
*/