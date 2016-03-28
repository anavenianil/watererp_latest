'use strict';

angular.module('watererpApp')
    .controller('FileUploadMasterDetailController', function ($scope, $rootScope, $stateParams, DataUtils, entity, FileUploadMaster) {
        $scope.fileUploadMaster = entity;
        $scope.load = function (id) {
            FileUploadMaster.get({id: id}, function(result) {
                $scope.fileUploadMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:fileUploadMasterUpdate', function(event, result) {
            $scope.fileUploadMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

        $scope.byteSize = DataUtils.byteSize;
    });
