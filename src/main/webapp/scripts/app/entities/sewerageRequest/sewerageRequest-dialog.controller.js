'use strict';

angular.module('watererpApp').controller(
		'SewerageRequestDialogController',
		/*
		 * ['$scope', '$stateParams', '$uibModalInstance', 'entity',
		 * 'SewerageRequest', 'TariffCategoryMaster', 'Receipt',
		 */
		function($scope, $stateParams, /* $uibModalInstance, entity, */
				SewerageRequest, TariffCategoryMaster, Receipt, $state,
				ParseLinks, SewerageChargeMaster) {

        $scope.sewerageRequest = {};//entity;
        //$scope.tariffcategorymasters = TariffCategoryMaster.query();
        //$scope.receipts = Receipt.query();
        $scope.load = function(id) {
            SewerageRequest.get({id : id}, function(result) {
                $scope.sewerageRequest = result;
            });
        };
        $scope.tariffcategorymasters = [];
        TariffCategoryMaster.query({page: $scope.page, size: 20, type:'METERED'}, function(result, headers) {
    		$scope.links = ParseLinks.parse(headers('link'));
    		for (var i = 0; i < result.length; i++) {
    			$scope.tariffcategorymasters.push(result[i]);
    		}
    	});
        $scope.sewerageRequest.requestedDate = new Date();

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:sewerageRequestUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('sewerageRequest');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.sewerageRequest.id != null) {
                SewerageRequest.update($scope.sewerageRequest, onSaveSuccess, onSaveError);
            } else {
                SewerageRequest.save($scope.sewerageRequest, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        $scope.datePickerForPaymentDate = {};

        $scope.datePickerForPaymentDate.status = {
            opened: false
        };

        $scope.datePickerForPaymentDateOpen = function($event) {
            $scope.datePickerForPaymentDate.status.opened = true;
        };
        
        var onRetrieval = function (result) {
        	$scope.sewerageRequest.amount = result.amount;
        };
        $scope.getSewerageAmount = function(categoryId){
        	$scope.sewerageCharge = SewerageChargeMaster.getChargeByCategoryId({categoryId:categoryId}, onRetrieval)
        	
        }
}/*]*/);
