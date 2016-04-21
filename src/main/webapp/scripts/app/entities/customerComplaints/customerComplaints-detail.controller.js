'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsDetailController', function ($scope, $rootScope, $stateParams, entity, CustomerComplaints, ComplaintTypeMaster) {
        $scope.customerComplaints = entity;
        $scope.load = function (id) {
            CustomerComplaints.get({id: id}, function(result) {
                $scope.customerComplaints = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:customerComplaintsUpdate', function(event, result) {
            $scope.customerComplaints = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
