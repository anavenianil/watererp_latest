'use strict';

angular.module('watererpApp')
    .controller('AccessListDialogController', function ($scope, $state, AccessList, ParseLinks, $stateParams) {

        $scope.accessLists = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        $scope.accessList = {};
        if($stateParams.accessListId != null){
        	$scope.accessListId = $stateParams.accessListId;
            //console.log($scope.accessListId);
            AccessList.get({id : $scope.accessListId}, function(result) {
                $scope.accessList = result;
            });
        }
        
        $scope.loadAll = function() {
            AccessList.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.accessLists.push(result[i]);
                }
            });
        };
        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:accessListUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('accessList');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.accessList.id != null) {
                AccessList.update($scope.accessList, onSaveSuccess, onSaveError);
            } else {
                AccessList.save($scope.accessList, onSaveSuccess, onSaveError);
                //$state.go('accessList');
            }
            //$state.go('accessList');
        };
        
        $scope.reset = function() {
            $scope.page = 0;
            $scope.accessLists = [];
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
            $scope.accessList = {
                userId: null,
                id: null
            };
        };
    });




/*'use strict';

angular.module('watererpApp').controller('AccessListDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'AccessList',
        function($scope, $stateParams, $uibModalInstance, entity, AccessList) {

        $scope.accessList = entity;
        $scope.load = function(id) {
            AccessList.get({id : id}, function(result) {
                $scope.accessList = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:accessListUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.accessList.id != null) {
                AccessList.update($scope.accessList, onSaveSuccess, onSaveError);
            } else {
                AccessList.save($scope.accessList, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);*/
