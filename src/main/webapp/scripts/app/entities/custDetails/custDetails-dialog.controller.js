'use strict';

angular.module('waterERPApp').controller('CustDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustDetails',
        function($scope, $stateParams, $uibModalInstance, entity, CustDetails) {

        $scope.custDetails = entity;
        $scope.load = function(id) {
            CustDetails.get({id : id}, function(result) {
                $scope.custDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:custDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.custDetails.id != null) {
                CustDetails.update($scope.custDetails, onSaveSuccess, onSaveError);
            } else {
                CustDetails.save($scope.custDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForConnDate = {};

        $scope.datePickerForConnDate.status = {
            opened: false
        };

        $scope.datePickerForConnDateOpen = function($event) {
            $scope.datePickerForConnDate.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
}]);
