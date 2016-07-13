'use strict';

angular.module('watererpApp')
    .controller('CustomerInfoController', function ($scope, $state, CustDetails, ParseLinks, $http) {

        $scope.custDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CustDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));                
                for (var i = 0; i < result.length; i++) {
                    $scope.custDetailss.push(result[i]);
                }
            });
        };
        
        $scope.reset = function() {
            $scope.page = 0;
            $scope.custDetailss = [];
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
        
        $scope.getLocation = function(val) {
        	//console.log("val	"+val);
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
        
        $scope.load = function(id) {
        	$scope.custDetailss=[];
        	CustDetails.get({
				id : id
			}, function(result) {
				//$scope.customerComplaints = result;
				$scope.custDetailss.push(result);
			});
		};
        
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");			
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			//$scope.findBillDetails($scope.custDetails.can);
			var val = $scope.custDetails.can;
			$http.get('api/custDetailss/search/' + val, {
				params : {
					address : val,
					sensor : false
				}
			}).then(function(response) {				
				$scope.load(response.data.id);
			});
		};

        $scope.clear = function () {
            $scope.custDetails = {
                can: null,
                divCode: null,
                secCode: null,
                secName: null,
                metReaderCode: null,
                connDate: null,
                consName: null,
                houseNo: null,
                address: null,
                city: null,
                pinCode: null,
                categoryUnused: null,
                pipeSize: null,
                boardMeter: null,
                sewerage: null,
                meterNo: null,
                prevBillType: null,
                prevBillMonth: null,
                prevAvgKl: null,
                metReadingDt: null,
                prevReading: null,
                metReadingMo: null,
                metAvgKl: null,
                arrears: null,
                reversalAmt: null,
                installment: null,
                otherCharges: null,
                surcharge: null,
                hrsSurcharge: null,
                resUnits: null,
                metCostInstallment: null,
                intOnArrears: null,
                lastPymtDt: null,
                lastPymtAmt: null,
                mobileNo: null,
                ccFlag: null,
                cpFlag: null,
                noticeFlag: null,
                drFlag: null,
                lat: null,
                longi: null,
                meterFixDate: null,
                id: null
            };
        };
    });
