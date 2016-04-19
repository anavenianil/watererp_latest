'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnController', function ($scope, $state, ApplicationTxn, ParseLinks, DateUtils, ApplicationTxnService, Principal) {

        $scope.applicationTxns = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        $scope.user = Principal.getLogonUser();
        console.log($scope.user);
        
        $scope.loadAll = function() {
            ApplicationTxn.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
        
        /*$scope.loadAll = function() {
            ApplicationTxn.query({page: $scope.page, size: 20, login: $scope.user.login}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };*/
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
        
        $scope.datePickerForapplicationTxnDt = {};

        $scope.datePickerForapplicationTxnDt.status = {
            opened: false
        };

        $scope.datePickerForapplicationTxnDtOpen = function($event) {
            $scope.datePickerForapplicationTxnDt.status.opened = true;
        };
        
        $scope.onSearch = function() {

        	var applicationTxnNo = document.getElementById("applicationTxnId").value;
            console.log("applicationTxnNo: "+applicationTxnNo);
            
            var applicationTxnDt = DateUtils.convertLocaleDateToServer($scope.applicationTxnDt);
            console.log("applicationTxnDt: "+$scope.applicationTxnDt);
            
            var statusSearch = document.getElementById("statusSearch").value;
            console.log("statusSearch: "+statusSearch);
            
            ApplicationTxnService.search(applicationTxnNo, applicationTxnDt, statusSearch).then(function (data) {
                $scope.applicationTxns = data;
            });
        }
    });
