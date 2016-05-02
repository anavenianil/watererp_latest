'use strict';

angular.module('watererpApp').controller(
		'OnlinePaymentOrderDialogController',
		function($scope, $stateParams, $http, $window, OnlinePaymentOrder,
				OnlinePaymentResponseSvc, MerchantMaster) {

			$scope.onlinePaymentOrder = {};
			$scope.isValidCust = false;
			$scope.merchantmasters = MerchantMaster.query();

			$scope.load = function(id) {
				OnlinePaymentOrder.get({
					id : id
				}, function(result) {
					$scope.onlinePaymentOrder = result;
				});
			};

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:onlinePaymentOrderUpdate', result);
				//$uibModalInstance.close(result);
				console.log("This is the result:" + JSON.stringify(result));
				$scope.isSaving = false;
				OnlinePaymentResponseSvc.findByOrder(result.id).then(
						function(result) {
							$window.location.href = result.redirectUrl;
						});

			};

			$scope.getCustomer = function(val) {
				$scope.isValidCust = false;

				return $http.get('api/custDetailss/searchCAN/' + val, {
					params : {
						address : val,
						sensor : false
					}
				}).then(function(response) {
					var res = response.data.map(function(item) {
						return item;
					});

					return res;
				});
			}

			$scope.onSelect = function($item, $model, $label) {
				var arr = $item.split("-");
				$scope.onlinePaymentOrder = {};
				$scope.onlinePaymentOrder.userDefinedField = arr[0];
				$scope.consName = arr[1];
				$scope.address = arr[2];
				$scope.custInfo = "";
				$scope.isValidCust = true;
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.save = function() {
				$scope.isSaving = true;
				if ($scope.onlinePaymentOrder.id != null) {
					OnlinePaymentOrder.update($scope.onlinePaymentOrder,
							onSaveSuccess, onSaveError);
				} else {
					OnlinePaymentOrder.save($scope.onlinePaymentOrder,
							onSaveSuccess, onSaveError);
				}
			};

			$scope.validate = function() {

				if ($scope.isSaving)
					return true;

				if (!$scope.isValidCust)
					return true;

				if (!$scope.editForm.amount.$dirty)
					return true;

				if (!$scope.editForm.payBy.$dirty)
					return true;

				if (!$scope.editForm.email.$dirty)
					return true;

				if ($scope.editForm.phone.$invalid)
					return true;

				return false;

			}

			$scope.clear = function() {
				$uibModalInstance.dismiss('cancel');
			};
		});
