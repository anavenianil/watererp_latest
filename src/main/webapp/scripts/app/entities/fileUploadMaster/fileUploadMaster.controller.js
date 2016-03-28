'use strict';

angular.module('watererpApp')
    .controller('FileUploadMasterController', function ($scope, $state, DataUtils, FileUploadMaster) {

        $scope.fileUploadMasters = [];
        $scope.loadAll = function() {
            FileUploadMaster.query(function(result) {
               $scope.fileUploadMasters = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.fileUploadMaster = {
                photo: null,
                photoContentType: null,
                textFile: null,
                binaryFile: null,
                binaryFileContentType: null,
                id: null
            };
        };

        $scope.abbreviate = DataUtils.abbreviate;

        $scope.byteSize = DataUtils.byteSize;
    });
