'use strict';

angular.module('waterERPApp')
    .controller('CustDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, CustDetails) {
        $scope.custDetails = entity;
        $scope.load = function (id) {
            CustDetails.get({id: id}, function(result) {
                $scope.custDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:custDetailsUpdate', function(event, result) {
            $scope.custDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
