'use strict';

angular.module('watererpApp')
    .controller('TerminalLogDialogController', function ($scope, $state, TerminalLog, ParseLinks, $stateParams) {

        $scope.terminalLogs = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            TerminalLog.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.terminalLogs.push(result[i]);
                }
            });
        };
        
        $scope.id=$stateParams.id;
        $scope.terminalLog = {};
        if($stateParams.id!=null){
        TerminalLog.get({id : $scope.id}, function(result) {
            $scope.terminalLog = result;
        });
    	}
        
        $scope.datePickerForLastModified = {};

        $scope.datePickerForLastModified.status = {
            opened: false
        };

        $scope.datePickerForLastModifiedOpen = function($event) {
            $scope.datePickerForLastModified.status.opened = true;
        };
        $scope.datePickerForBankDepositDate = {};

        $scope.datePickerForBankDepositDate.status = {
            opened: false
        };

        $scope.datePickerForBankDepositDateOpen = function($event) {
            $scope.datePickerForBankDepositDate.status.opened = true;
        };
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:terminalLogUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('terminalLog');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.terminalLog.id != null) {
                TerminalLog.update($scope.terminalLog, onSaveSuccess, onSaveError);
            } else {
                TerminalLog.save($scope.terminalLog, onSaveSuccess, onSaveError);
            }
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.terminalLogs = [];
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
            $scope.terminalLog = {
                amount: null,
                lastModified: null,
                modifiedBy: null,
                userId: null,
                bankDepositDate: null,
                beforeUpdate: null,
                afterUpdate: null,
                mrCode: null,
                remark: null,
                txnType: null,
                id: null
            };
        };
    });





/*'use strict';

angular.module('watererpApp').controller('TerminalLogDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TerminalLog',
        function($scope, $stateParams, $uibModalInstance, entity, TerminalLog) {

        $scope.terminalLog = entity;
        $scope.load = function(id) {
            TerminalLog.get({id : id}, function(result) {
                $scope.terminalLog = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:terminalLogUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.terminalLog.id != null) {
                TerminalLog.update($scope.terminalLog, onSaveSuccess, onSaveError);
            } else {
                TerminalLog.save($scope.terminalLog, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForLastModified = {};

        $scope.datePickerForLastModified.status = {
            opened: false
        };

        $scope.datePickerForLastModifiedOpen = function($event) {
            $scope.datePickerForLastModified.status.opened = true;
        };
        $scope.datePickerForBankDepositDate = {};

        $scope.datePickerForBankDepositDate.status = {
            opened: false
        };

        $scope.datePickerForBankDepositDateOpen = function($event) {
            $scope.datePickerForBankDepositDate.status.opened = true;
        };
}]);
*/