'use strict';

angular.module('watererpApp')
    .controller('VersionDialogController', function ($scope, $state, Version, ParseLinks, $stateParams) {

        $scope.versions = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            Version.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.versions.push(result[i]);
                }
            });
        };
        $scope.version ={};
        $scope.versionId=$stateParams.versionId;
        if($stateParams.versionId!=null){
        Version.get({id : $scope.versionId}, function(result) {
            $scope.version = result;
        });
        }
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:versionUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('version');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.version.id != null) {
                Version.update($scope.version, onSaveSuccess, onSaveError);
            } else {
                Version.save($scope.version, onSaveSuccess, onSaveError);
            }
        };        
        $scope.reset = function() {
            $scope.page = 0;
            $scope.versions = [];
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
            $scope.version = {
                versionLow: null,
                versionHigh: null,
                id: null
            };
        };
    });
















/*'use strict';

angular.module('watererpApp').controller('VersionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Version',
        function($scope, $stateParams, $uibModalInstance, entity, Version) {

        $scope.version = entity;
        $scope.load = function(id) {
            Version.get({id : id}, function(result) {
                $scope.version = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:versionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.version.id != null) {
                Version.update($scope.version, onSaveSuccess, onSaveError);
            } else {
                Version.save($scope.version, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
*/