'use strict';

angular.module('watererpApp')
    .controller('BillDetailsDialogController', function ($scope, $state, BillDetails, CustDetails, CustDetailsService, ParseLinks, $stateParams, $http) {

        $scope.billDetailss = [];
        $scope.billDetails = {};
        $scope.predicate = 'id';
        $scope.collDetails = {};
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            BillDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.billDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.billDetailss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();
        
        $scope.billDetailsId = $stateParams.id;
        if($stateParams.id != null){       	
        	 BillDetails.get({id : $scope.billDetailsId}, function(result) {
                 $scope.billDetails = result;
                 $scope.getCustDetails($scope.billDetails.can);
             });
    	}        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billDetailsUpdate', result);
            $scope.isSaving = false;
            $scope.billDetails.id = result.id;
            $state.go('billDetails');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };
        
        

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billDetails.id != null) {
                BillDetails.update($scope.billDetails, onSaveSuccess, onSaveError);
            } else {
                BillDetails.save($scope.billDetails, onSaveSuccess, onSaveError);
            }
        };


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };
        
        $scope.getLocation = function(val) {
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
			console.log($item);
			var arr = $item.split("-");
			$scope.billDetails = {};
			$scope.billDetails.can = arr[0];
			$scope.billDetails.consName = arr[1];
			$scope.billDetails.address = arr[2];
			$scope.custInfo = "";
			$scope.isValidCust = true;
		};

        
        $scope.clear = function () {
            $scope.billDetails = {
                can: null,
                billNumber: null,
                billDate: null,
                billTime: null,
                meterMake: null,
                currentBillType: null,
                fromMonth: null,
                toMonth: null,
                meterFixDate: null,
                initialReading: null,
                presentReading: null,
                units: null,
                waterCess: null,
                sewerageCess: null,
                serviceCharge: null,
                meterServiceCharge: null,
                totalAmount: null,
                netPayableAmount: null,
                telephoneNo: null,
                meterStatus: null,
                metReaderCode: null,
                billFlag: null,
                svrStatus: null,
                terminalId: null,
                meterReaderId: null,
                userId: null,
                mobileNo: null,
                noticeNo: null,
                lat: null,
                longi: null,
                noMeterAmt: null,
                metReadingDt: null,
                id: null
            };
        };
        
        
        $scope.disEnableSearch = function(enableSearch){ 
            $scope.enableSearch = enableSearch;
            	if($scope.enableSearch==undefined){
            		$scope.enableSearch='';
            	}        	
            	if($scope.enableSearch.length > 0 && !isNaN($scope.enableSearch)){
            		document.getElementById("submitSearch").disabled=false;
            	}
            	else{
            		document.getElementById("submitSearch").disabled=true;
            	}
            }
        
        $scope.getCustDetails = function(can){
        	CustDetailsService.get({can : can}, function(result) {
                $scope.custDetails = result;
                $scope.billDetails.consName = $scope.custDetails.consName;
                $scope.billDetails.can = $scope.custDetails.can;
                $scope.billDetails.address = $scope.custDetails.address;
                $scope.billDetails.prevBillMonth = $scope.custDetails.prevBillMonth;
            });
        }
        
        $scope.billDetails = {
        		currentBillType: null,
        		currentBillTypes: [
        	      {id: 'M', name: 'METERED'},
        	      {id: 'U', name: 'UNMETERED'},
        	      {id: 'L', name: 'LOCKED'},
        	      {id: 'R', name: 'RUNNING'},
        	    ],
        	   };
        
        $scope.datePickerForToMonth = {};

        $scope.datePickerForToMonth.status = {
            opened: false
        };

        $scope.datePickerForToMonthOpen = function($event) {
            $scope.datePickerForToMonth.status.opened = true;
        }; 
        
        $scope.datePickerForFromMonth = {};

        $scope.datePickerForFromMonth.status = {
            opened: false
        };

        $scope.datePickerForFromMonthOpen = function($event) {
            $scope.datePickerForFromMonth.status.opened = true;
        };        
        
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };
        $scope.datePickerForMeterFixDate = {};

        $scope.datePickerForMeterFixDate.status = {
            opened: false
        };

        $scope.datePickerForMeterFixDateOpen = function($event) {
            $scope.datePickerForMeterFixDate.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
        
    });