'use strict';
angular.module('watererpApp')
    .controller('CollectionDetailsYearlyReportController', function ($scope, $window, $state, $filter,  $http, 
    		     
    		TariffCategoryMaster, DivisionMaster) {

        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.custDetails = {};
        $scope.collDetails = {};
        $scope.collDetails.betweenDates = false;
        $scope.message = null;
        $scope.collDetails.betweenDetailedDates =false;
        $scope.getDivisionMasters = function() {
        	$scope.divisionmasters = [];
			return $http.get('/api/divisionMasters/getAll').then(function(response) {
						$scope.divisionmasters = response.data;
					});
		}
        $scope.getDivisionMasters();
        
        $scope.getTariffCategoryMasters = function() {
        	$scope.tariffcategorymasters = [];
			return $http.get('/api/tariffCategoryMasters/getAll').then(function(response) {
						$scope.tariffcategorymasters = response.data;
					});
		}
        $scope.getTariffCategoryMasters();
        
        $scope.datePickerFordateOfYear = {};

		$scope.datePickerFordateOfYear.status = {
			opened : false
		};

		$scope.datePickerFordateOfYearOpen = function($event) {
			$scope.datePickerFordateOfYear.status.opened = true;
		};

		$scope.datepickerOptions = {
			    datepickerMode:"'year'",
			    minMode:"'year'",
			    minDate:"minDate",
			    showWeeks:"false",
			 };
		
		$scope.datepickerOptions = {
			    datepickerMode:"'month'",
			    minMode:"'month'",
			    minDate:"minDate",
			    showWeeks:"false",
			    format: "MM-yyyy",
			 };
	
		
        $scope.getReport = function () {
     	if($scope.custDetails.divisionMaster != null &&  $scope.custDetails.tariffCategoryMaster != null && $scope.collDetails.dateOfYear != null && $scope.collDetails.dateOfMonth!= null ){
     	var divisionId = $scope.custDetails.divisionMaster.id;
     	var tariffCategoryId = $scope.custDetails.tariffCategoryMaster.id;        	
         var dateFormat = 'yyyy';
         var dateFormat1 = 'MMMM';
         var dateOfYear = $filter('date')($scope.collDetails.dateOfYear, dateFormat);
         var dateOfMonth = $filter('date')($scope.collDetails.dateOfMonth, dateFormat1);

         var formatDate =  function (dateToFormat) {
             if (dateToFormat !== undefined && !angular.isString(dateToFormat)) {
                 return dateToFormat.getYear() + '-' + dateToFormat.getMonth() + '-' + dateToFormat.getDay();
             }
             return dateToFormat;
         };

         $scope.collDetails = {};
         
         	$window.open('/api/collDetailss/reports/' + divisionId + '/' + tariffCategoryId + '/' + formatDate(dateOfYear) + '/' + formatDate(dateOfMonth), "_blank")
             location.reload();
         }
       
         
         
     };
  /*      
        var onSaveSuccess = function (result) {
          
            $scope.isSaving = false;
            $scope.custDetails.fromdate = result.id;
           
            $scope.clear();
			$scope.rc.editForm.attempted=false;
			$scope.editForm.$setPristine();
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };
*/
        $scope.getReport1 = function () {
       
            if($scope.custDetails.divisionMaster == null &&  $scope.custDetails.tariffCategoryMaster == null && $scope.collDetails.dateOfYear == null && $scope.collDetails.dateOfMonth == null ) 
            {
            	
            	$scope.editForm.$setDirty();
            
            }
        };
    
        
        
        
           $scope.getReportDetails = function () {
        	if($scope.custDetails.divisionMaster != null &&  $scope.custDetails.tariffCategoryMaster != null && $scope.collDetails.dateOfYear != null && $scope.collDetails.dateOfMonth!= null ){
        	var divisionId = $scope.custDetails.divisionMaster.id;
        	var tariffCategoryId = $scope.custDetails.tariffCategoryMaster.id;        	
            var dateFormat = 'yyyy';
            var dateFormat1 = 'MMMM';
            var dateOfYear = $filter('date')($scope.collDetails.dateOfYear, dateFormat);
            var dateOfMonth = $filter('date')($scope.collDetails.dateOfMonth, dateFormat1);
            var formatDate =  function (dateToFormat) {
                if (dateToFormat !== undefined && !angular.isString(dateToFormat)) {
                    return dateToFormat.getYear() + '-' + dateToFormat.getMonth() + '-' + dateToFormat.getDay();
                }
                return dateToFormat;
            };

            $scope.collDetails = {};
            
            	$window.open('/api/collDetailss/reportDetails/' + divisionId + '/' + tariffCategoryId + '/' + formatDate(dateOfYear) + '/' + formatDate(dateOfMonth), "_blank")
                location.reload();
            }
          
            
            
        };
        
   

		
		 $scope.datePickerFordateOfMonth = {};

			$scope.datePickerFordateOfMonth.status = {
				opened : false
			};

			$scope.datePickerFordateOfMonthOpen = function($event) {
				$scope.datePickerFordateOfMonth.status.opened = true;
			};
    });



