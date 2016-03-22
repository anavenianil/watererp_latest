'use strict';

angular.module('watererpApp')
    .controller('CustomerController', function ($scope, $state, Customer, ParseLinks) {

        $scope.customers = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            Customer.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.customers.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.customers = [];
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
            $scope.customer = {
                requestDate: null,
                firstName: null,
                middleName: null,
                lastName: null,
                houseNo: null,
                govtOfficialNo: null,
                ward: null,
                street: null,
                pincode: null,
                block: null,
                area: null,
                section: null,
                constituency: null,
                email: null,
                telOffice: null,
                telHome: null,
                mobile: null,
                fileNumber: null,
                id: null
            };
        };
    });
