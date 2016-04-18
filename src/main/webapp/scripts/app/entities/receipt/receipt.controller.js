'use strict';

angular.module('watererpApp')
    .controller('ReceiptController', function ($scope, $state, Receipt) {

        $scope.receipts = [];
        $scope.loadAll = function() {
            Receipt.query(function(result) {
               $scope.receipts = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.receipt = {
                amount: null,
                bankName: null,
                branchName: null,
                checkOrDdDate: null,
                checkOrDdNo: null,
                receiptDate: null,
                id: null
            };
        };
    });
