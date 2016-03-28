'use strict';

angular.module('waterERPApp')
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
                bill_number: null,
                bill_date: null,
                bill_time: null,
                meter_make: null,
                current_bill_type: null,
                from_month: null,
                to_month: null,
                meter_fix_date: null,
                initial_reading: null,
                present_reading: null,
                units: null,
                water_cess: null,
                sewerage_cess: null,
                service_charge: null,
                meter_service_charge: null,
                total_amount: null,
                net_payable_amount: null,
                telephone_no: null,
                meter_status: null,
                mc_met_reader_code: null,
                bill_flag: null,
                svr_status: null,
                terminal_id: null,
                meter_reader_id: null,
                user_id: null,
                mobile_no: null,
                notice_no: null,
                lat: null,
                longI: null,
                nometer_amt: null,
                id: null
            };
        };
    });
