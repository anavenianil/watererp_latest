'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsController', function ($scope, $state, CustomerComplaints, ParseLinks, $http) {

        $scope.customerComplaintss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;

        $scope.page = 0;
        $scope.loadAll = function() {
            CustomerComplaints.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.customerComplaintss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.customerComplaintss = [];
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
        
        $scope.getCustomerComplaint = function(val){
        	return $http.get('api/customerComplaintss/searchCustomerComplaint/' + val, {
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
			console.log($item);
			var arr = $item.split("-");

			/*$scope.collDetails.can = arr[0];
			$scope.collDetails.consName = arr[1];
			$scope.collDetails.address = arr[2];
			$scope.custInfo = "";
			$scope.isValidCust = true;

			CustDetailsService
					.get(
							{
								can : $scope.collDetails.can
							},
							function(result) {
								$scope.custDetails = result;
								$scope.customerComplaints.custDetails.consName = $scope.custDetails.consName;
								$scope.customerComplaints.can = $scope.custDetails.can;
								$scope.customerComplaints.tariffCategory = $scope.custDetails.tariffCategoryMaster.tariffCategory;
							});*/
		};

        $scope.clear = function () {
            $scope.customerComplaints = {
                remarks: null,
                relevantDoc: null,
                complaintBy: null,
                complaintDate: null,
                can: null,
                id: null
            };
        };
    });
