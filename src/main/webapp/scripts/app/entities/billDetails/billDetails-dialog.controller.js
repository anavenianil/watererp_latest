'use strict';

angular.module('watererpApp').controller(
		'BillDetailsDialogController', 
		function ($scope, $state, BillDetails, CustDetails, CustDetailsService, 
				 ParseLinks, $stateParams, $http, User) {

        $scope.billDetailss = [];
        $scope.predicate = 'id';
        $scope.billDetails = {};
        $scope.collDetails = {};
        var date  = new Date();
        $scope.billDetails.billDate = date;
        $scope.billDetails.toMonth = new Date(date.getFullYear(), date.getMonth(), 1);
        $scope.users = User.query();
        $scope.reverse = true;
        $scope.page = 0;
        
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
        	$scope.clear();
        	$scope.billDetails.billDate = new Date();
			console.log($item);
			var arr = $item.split("-");
			$scope.billDetails.can = arr[0];
			$scope.billDetails.consName = arr[1];
			$scope.billDetails.address = arr[2];
			$scope.custInfo = "";
			$scope.getCustDetails($scope.billDetails.can);
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
                currentBillTypes: [
                         	      {id: 'M', name: 'METERED'},
                         	      {id: 'S', name: 'STUCK'},
                         	      {id: 'L', name: 'LOCKED'},
                         	      {id: 'B', name: 'BURNT'},
                         	    ],
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
        
        $scope.toggleMeterReadingDate = function(cbtyp){
        	if(cbtyp==='M'){
        		$scope.billDetails.prevMetReadingDt1 = $scope.billDetails.billDate;
        	}else{
        		$scope.billDetails.prevMetReadingDt1 = $scope.billDetails.prevMetReadingDt;
        	}
        }
        $scope.getCustDetails = function(can){
        	CustDetailsService.get({can : can}, function(result) {
                $scope.custDetails = result;
                $scope.billDetails.consName = $scope.custDetails.consName;
                $scope.billDetails.can = $scope.custDetails.can;
                $scope.billDetails.address = $scope.custDetails.address;
                $scope.billDetails.prevBillMonth = $scope.custDetails.prevBillMonth;
                var date2 = new Date($scope.billDetails.prevBillMonth);
                $scope.billDetails.fromMonth = new Date(date2.getFullYear(), date2.getMonth()+1, date2.getDate());
                $scope.billDetails.initialReading = $scope.custDetails.prevReading;
                $scope.billDetails.prevMetReadingDt = $scope.custDetails.metReadingDt;
                $scope.billDetails.prevMetReadingDt1 = $scope.custDetails.metReadingDt;
            });
        }
        
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