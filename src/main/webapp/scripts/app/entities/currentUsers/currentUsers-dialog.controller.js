'use strict';

angular.module('watererpApp')
    .controller('CurrentUsersDialogController', function ($scope, $state, CurrentUsers, ParseLinks, $stateParams) {

        $scope.currentUserss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CurrentUsers.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.currentUserss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.currentUserss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();
        
        $scope.datePickerForLoginTime = {};

        $scope.datePickerForLoginTime.status = {
            opened: false
        };

        $scope.datePickerForLoginTimeOpen = function($event) {
            $scope.datePickerForLoginTime.status.opened = true;
        };
        
        $scope.currentUsers = {};  
        //alert("$stateParams.currentUsersId		"+$stateParams.currentUsersId);
        $scope.currentUsersId1 = $stateParams.id;
        if($stateParams.id != null){
        	CurrentUsers.get({id : $scope.currentUsersId1}, function(result) {
                $scope.currentUsers = result;
            });	
        }
        
        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:currentUsersUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('currentUsers');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.currentUsers.id != null) {
                CurrentUsers.update($scope.currentUsers, onSaveSuccess, onSaveError);
            } else {
                CurrentUsers.save($scope.currentUsers, onSaveSuccess, onSaveError);
            }
        };


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.currentUsers = {
                terminalId: null,
                meterReaderId: null,
                userId: null,
                requestType: null,
                loginTime: null,
                ip: null,
                id: null
            };
        };
    });





/*'use strict';

angular.module('watererpApp').controller('CurrentUsersDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CurrentUsers',
        function($scope, $stateParams, $uibModalInstance, entity, CurrentUsers) {

        $scope.currentUsers = entity;
        $scope.load = function(id) {
            CurrentUsers.get({id : id}, function(result) {
                $scope.currentUsers = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:currentUsersUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.currentUsers.id != null) {
                CurrentUsers.update($scope.currentUsers, onSaveSuccess, onSaveError);
            } else {
                CurrentUsers.save($scope.currentUsers, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForLoginTime = {};

        $scope.datePickerForLoginTime.status = {
            opened: false
        };

        $scope.datePickerForLoginTimeOpen = function($event) {
            $scope.datePickerForLoginTime.status.opened = true;
        };
}]);
*/