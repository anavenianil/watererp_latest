'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnController', function ($scope, $state, ApplicationTxn, ParseLinks) {

        $scope.applicationTxns = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ApplicationTxn.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.applicationTxns = [];
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
            $scope.applicationTxn = {
                firstName: null,
                middleName: null,
                lastName: null,
                organization: null,
                organizationName: null,
                designation: null,
                mobileNo: null,
                officeNo: null,
                email: null,
                street: null,
                plotNo: null,
                blockNo: null,
                tanescoMeter: null,
                waterConnectionUse: null,
                bStreet: null,
                ward: null,
                dma: null,
                bPlotNo: null,
                regiterMobile: null,
                attachedDocType: null,
                idNumber: null,
                propertyDoc: null,
                can: null,
                photo: null,
                status: null,
                meterReading: null,
                connectionDate: null,
                remarks: null,
                meterNo: null,
                approvedDate: null,
                id: null
            };
        };
    });
