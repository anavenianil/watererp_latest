'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDialogController', function ($scope, $state, $stateParams ,ApplicationTxn, ParseLinks, TariffCategoryMaster, 
    		UploadUtil, DateUtils, User, IdProofMaster, DivisionMaster, StreetMaster) {
    	$scope.applicationTxn = {};
    	$scope.users = User.query();
    	$scope.divisionmasters = DivisionMaster.query();
    	$scope.idProofMasters = IdProofMaster.query();
    	$scope.tariffcategorymasters = [];

    	TariffCategoryMaster.query({page: $scope.page, size: 20, type:'METERED'}, function(result, headers) {
    		$scope.links = ParseLinks.parse(headers('link'));
    		for (var i = 0; i < result.length; i++) {
    			$scope.tariffcategorymasters.push(result[i]);
    		}
    	});
    	
    	if($stateParams.id != null){
                ApplicationTxn.get({id : $stateParams.id}, function(result) {
                    $scope.applicationTxn = result;
                });
         }
    	
    	$scope.applicationTxn.requestedDate = new Date();
    	$scope.dtmax = new Date();
    	
    	$scope.getStreet = function(divisionId){
        	$scope.streetmasters = [];
            StreetMaster.query({page: $scope.page, size: 20, divisionId: divisionId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.streetmasters.push(result[i]);
                }
            });
        }
    	
    	//$scope.applicationTxn.requestedDate = DateUtils.convertDateTimeFromServer(new Date());
    	/*$scope.applicationTxn.requestedDate = DateService.getServerDate();
    	console.log($scope.applicationTxn.requestedDate);*/
        
    	
    	/*$scope.load = function(id) {
            ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
            });
        };*/

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.isSaving = false;
            $scope.applicationTxn.id = result.id;
            $('#saveSuccessfullyModal').modal('show');
            //$scope.clear();
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } else {
                ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            }
        };

        /*$scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };*/
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForUpdatedDate = {};

        $scope.datePickerForUpdatedDate.status = {
            opened: false
        };

        $scope.datePickerForUpdatedDateOpen = function($event) {
            $scope.datePickerForUpdatedDate.status.opened = true;
        };
        
        
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
        
        $scope.clear = function(){
        	$scope.applicationTxn = {firstName: null, middleName: null, lastName: null, organization: null, organizationName: null,
        			designation: null, mobileNo: null,  officeNo: null,  email: null, street: null,
        			plotNo: null, blockNo: null, tanescoMeter: null, waterConnectionUse: null, categoryMaster: null, bStreet: null, ward: null,
        			dma: null, bPlotNo: null, regiterMobile: null, attachedDocType: null, idNumber : null, propertyDoc: null, can : null,
        			photo : null, status: null, meterReading : null, connectionDate : null, remarks : null, meterNo: null, approvedDate : null,
        			meterDetails : null, user : null, requestAt : null
            };
        	$scope.rc.editForm.attempted=false;
			$scope.editForm.$setPristine();
        }
        
        
        $scope.$watch('applicationTxn.photo1', function (files) {
            $scope.formUpload = false;
            if (files != null) {
                for (var i = 0; i < files.length; i++) {
                    $scope.errorMsg = null;
                    (function (file) {
                    	UploadUtil.uploadUsingUpload(file, $scope, 'waterErp');
                    })(files[i]);
                }
            }
        });
        
        $scope.getReqParams = function() {
			return $scope.generateErrorOnServer ? '?errorCode='
					+ $scope.serverErrorCode + '&errorMessage='
					+ $scope.serverErrorMsg : '';
		};
		
		
		$scope.disableOrg = function(categoryId){
			if(categoryId === 1){
				$scope.applicationTxn.organization = false;
				$scope.applicationTxn.organizationName = "";
				$scope.applicationTxn.designation = "";
			}
		}
/*		$scope.clickBox = function(categoryId){
			alert("clicked");
			if(categoryId === 1){
				
				$scope.applicationTxn.organizationName =  false;
				$scope.rc.editForm.attempted=false;
				$scope.editForm.$setPristine();
			}
		}*/
		
		
		
		$scope.getPropertyVal = function(val1, val2){
			$scope.applicationTxn.propertyDoc ="";
			if (val1 == null){
				$scope.applicationTxn.propertyDoc = val2;
			}
			if (val2 == null){
				$scope.applicationTxn.propertyDoc = val1;
			}
			else
			{
				$scope.applicationTxn.propertyDoc = val1 +" & " + val2;
			}
			console.log($scope.applicationTxn.propertyDoc);
		}
		

		
/*$scope.checkForm=function(value1,value2){
	alert("check");
if(value1=="checked"|| value2=="checked")
	{
	$scope.editForm.applicationTxn.deedDoc.$setValidity(
			"ltPrevious", true);
	return true;
	else 
		$scope.editForm.applicationTxn.deedDoc.$setValidity(
				"ltPrevious", false);
	return false;
	} 
 if(value1=="checked"|| value2=="checked")
{
       $scope.editForm.applicationTxn.agreementDoc.$setValidity(
		"ltPrevious", true);
       return true;

	else
		 $scope.editForm.applicationTxn.agreementDoc.$setValidity(
					"ltPrevious", false);
	return false;
}		
else
	{
	alert("fail");
	}
	}*/
		

		

	
    });