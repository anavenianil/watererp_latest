'use strict';

angular.module('watererpApp')
    .controller('BillDetailsController', function ($scope, $state, BillDetails, ParseLinks) {

        $scope.billDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            BillDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.billDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.billDetailss = [];
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
            $scope.billDetails = {
                can: null,
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
                metReaderCode: null,
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
