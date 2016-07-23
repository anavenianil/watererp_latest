'use strict';

angular.module('watererpApp')
    .controller('CustDetailsReportController', function ($scope, $state, CustDetails, ParseLinks, $http, 
    		TariffCategoryMaster, DivisionMaster) {

        $scope.custDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.divisionmasters = DivisionMaster.query();
        
        
     	
    });
