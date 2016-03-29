'use strict';

angular.module('watererpApp').factory(
		'UploadUtil',
		function($timeout, Upload) {

			return {				

				uploadUsingUpload : function(file, scope, objectType) {
					file.upload = Upload.upload({
						url : '/api/upload' + scope.getReqParams(),
						method : 'POST',
						headers : {
							'my-header' : 'my-header-value'
						},
						fields : {
							username : 'admin',
							objectType : objectType
						},
						file : file,
						fileFormDataName : 'file'
					});

					file.upload.then(function(response) {
						$timeout(function() {
							file.result = response.data;
						});
					}, function(response) {
						if (response.status > 0)
							scope.errorMsg = response.status + ': '
									+ response.data;
					});

					file.upload.progress(function(evt) {
						// Math.min is to fix IE which reports 200% sometimes
						file.progress = Math.min(100, parseInt(100.0
								* evt.loaded / evt.total));
					});

					file.upload.xhr(function(xhr) {
						// xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
					});
				}
			};

			angular.element(window).bind('dragover', function(e) {
				e.preventDefault();
			});
			angular.element(window).bind('drop', function(e) {
				e.preventDefault();
			});
		});
