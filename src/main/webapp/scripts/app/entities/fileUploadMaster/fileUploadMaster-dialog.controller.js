'use strict';

angular.module('watererpApp').controller('FileUploadMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'FileUploadMaster',
        function($scope, $stateParams, $uibModalInstance, DataUtils, entity, FileUploadMaster) {

        $scope.fileUploadMaster = entity;
        $scope.load = function(id) {
            FileUploadMaster.get({id : id}, function(result) {
                $scope.fileUploadMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:fileUploadMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.fileUploadMaster.id != null) {
                FileUploadMaster.update($scope.fileUploadMaster, onSaveSuccess, onSaveError);
            } else {
                FileUploadMaster.save($scope.fileUploadMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.abbreviate = DataUtils.abbreviate;

        $scope.byteSize = DataUtils.byteSize;

        $scope.setPhoto = function ($file, fileUploadMaster) {
            if ($file && $file.$error == 'pattern') {
                return;
            }
            if ($file) {
                var fileReader = new FileReader();
                fileReader.readAsDataURL($file);
                fileReader.onload = function (e) {
                    var base64Data = e.target.result.substr(e.target.result.indexOf('base64,') + 'base64,'.length);
                    $scope.$apply(function() {
                        fileUploadMaster.photo = base64Data;
                        fileUploadMaster.photoContentType = $file.type;
                    });
                };
            }
        };

        $scope.setBinaryFile = function ($file, fileUploadMaster) {
            if ($file) {
                var fileReader = new FileReader();
                fileReader.readAsDataURL($file);
                fileReader.onload = function (e) {
                    var base64Data = e.target.result.substr(e.target.result.indexOf('base64,') + 'base64,'.length);
                    $scope.$apply(function() {
                        fileUploadMaster.binaryFile = base64Data;
                        fileUploadMaster.binaryFileContentType = $file.type;
                    });
                };
            }
        };
}]);
