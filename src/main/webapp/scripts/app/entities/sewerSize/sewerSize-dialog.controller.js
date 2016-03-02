'use strict';

angular.module('watererpApp')
    .controller('SewerSizeDialogController', function ($scope, $state, SewerSize, ParseLinks, $uibModalInstance) {

        $scope.sewerSizes = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            SewerSize.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.sewerSizes.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.sewerSizes = [];
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
            $scope.sewerSize = {
                sewerSize: null,
                id: null
            };
        };
        
        $scope.showUpdate = function (id) {
        	 $state.go('sewerSize');
            SewerSize.get({id: id}, function(result) {
                $scope.sewerSize = result;
                //$('#viewSewerSizeModal').modal('hide');
                $uibModalInstance.dismiss('cancel');
               
            });
        };
    });
