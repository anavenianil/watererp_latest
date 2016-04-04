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
        
//        $scope.getApplicationTxn = function(id){
//        	$state.go('applicationTxn.detail');
//        }
        
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
                fullName: null,
                homeOrOficeNumber: null,
                regionalNumber: null,
                faxNumber: null,
                plotNumber: null,
                area: null,
                street: null,
                villageExecutiveOffice: null,
                villageExecutiveOfficeNumber: null,
                house: null,
                institution: null,
                business: null,
                industry: null,
                poBox: null,
                requestedDate: null,
                photo: null,
                fileNumber: null,
                createdDate: null,
                updatedDate: null,
                status: null,
                id: null
            };
        };
    });
