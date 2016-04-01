'use strict';

angular.module('watererpApp')
    .controller('TerminalDialogController', function ($scope, $state, Terminal, ParseLinks, $stateParams) {

        $scope.terminals = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            Terminal.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.terminals.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.terminals = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();
        
        $scope.terminalId = $stateParams.id;
        $scope.terminal = {};
        if($stateParams.id!=null){
        	Terminal.get({id : $scope.terminalId}, function(result) {
                $scope.terminal = result;
            });	
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:terminalUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.terminal.id != null) {
                Terminal.update($scope.terminal, onSaveSuccess, onSaveError);
            } else {
                Terminal.save($scope.terminal, onSaveSuccess, onSaveError);
            }
        };
        
        


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.terminal = {
                amount: null,
                status: null,
                userId: null,
                mrCode: null,
                secCode: null,
                divCode: null,
                secName: null,
                userName: null,
                mobileNo: null,
                ver: null,
                id: null
            };
        };
    });







































/*'use strict';

angular.module('watererpApp').controller('TerminalDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Terminal',
        function($scope, $stateParams, $uibModalInstance, entity, Terminal) {

        $scope.terminal = entity;
        $scope.load = function(id) {
            Terminal.get({id : id}, function(result) {
                $scope.terminal = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:terminalUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.terminal.id != null) {
                Terminal.update($scope.terminal, onSaveSuccess, onSaveError);
            } else {
                Terminal.save($scope.terminal, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
*/