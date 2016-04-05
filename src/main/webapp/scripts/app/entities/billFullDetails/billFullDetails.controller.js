'use strict';

angular.module('watererpApp')
    .controller('BillFullDetailsController', function ($scope, $state, BillFullDetails, ParseLinks) {

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


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.billFullDetails = {
                can: null,
                divCode: null,
                secCode: null,
                secName: null,
                metReaderCode: null,
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
                surcharge: null,
                hrsSurcharge: null,
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
                billFlag: null,
                svrStatus: null,
                terminalId: null,
                meterReaderId: null,
                userId: null,
                mobileNo: null,
                noticeNo: null,
                lat: null,
                longi: null,
                noMeterAmt: null,
                id: null
            };
        };
    });
