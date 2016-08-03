/* globals $ */
'use strict';

angular.module('watererpApp')
    .directive('showValidation', function() {
        return {
            restrict: 'A',
            require: 'form',
            link: function (scope, element/*, attrs, formCtrl*/) {
                element.find('.form-group').each(function() {
                    var $formGroup = $(this);
                    var $inputs = $formGroup.find('input[ng-model],textarea[ng-model],select[ng-model]');

                    if ($inputs.length > 0) {
                        $inputs.each(function() {
                            var $input = $(this);
                            /*var inputName = $input.attr('name');//newly added*/
                            scope.$watch(function() {
                            	return $input.hasClass('ng-invalid') && $input.hasClass('ng-dirty');//was removed 
                                //return formCtrl[inputName].$invalid && formCtrl[inputName].$dirty;//newly added
                            }, function(isInvalid) {
                                $formGroup.toggleClass('has-error', isInvalid);
                            });
                        });
                    }
                });
            }
        };
    })
    .directive('rcSubmit', function ($parse, $q, $timeout) {
        return {
          restrict: 'A',
          require: ['rcSubmit', '?form'],
          controller: ['$scope', function ($scope) {
     
     		var formElement = null;
            var formController = null;
            var attemptHandlers = [];
            var submitCompleteHandlers = [];
     
            this.attempted = false;
            this.submitInProgress = false;
            
            this.setFormElement = function(element) {
              formElement = element;
            }
            
            this.submit = function() {
              if (!formElement) return;
              
              jQuery(formElement).submit();
            }
            
            this.onAttempt = function(handler) {
              attemptHandlers.push(handler);
            };
     
            this.setAttempted = function() {
              this.attempted = true;
              
              angular.forEach(attemptHandlers, function (handler) {
                handler();
              });
            };
     
            this.setFormController = function(controller) {          
              formController = controller;
            };
     
            this.needsAttention = function (fieldModelController) {
              if (!formController) return false;
     
              if (fieldModelController) {
                return fieldModelController.$invalid && 
                       (fieldModelController.$dirty || this.attempted);
              } else {
                return formController && formController.$invalid && 
                       (formController.$dirty || this.attempted);
              }
            };
     
            this.onSubmitComplete = function (handler) {
     
              submitCompleteHandlers.push(handler);
            };
     
            this.setSubmitComplete = function (success, data) {
     
              angular.forEach(submitCompleteHandlers, function (handler) {
                handler({ 'success': success, 'data': data });
              });
            };
          }],
          compile: function(cElement, cAttributes, transclude) {
            return {
              pre: function(scope, formElement, attributes, controllers) {
     
                var submitController = controllers[0];
                var formController = (controllers.length > 1) ? controllers[1] : null;
     
     			submitController.setFormElement(formElement);
                submitController.setFormController(formController);
     
                scope.rc = scope.rc || {};
                scope.rc[attributes.name] = submitController;
              },
              post: function(scope, formElement, attributes, controllers) {
     
                var submitController = controllers[0];
                var formController = (controllers.length > 1) ? controllers[1] : null;
                var fn = $parse(attributes.rcSubmit);
     
                formElement.bind('submit', function (event) {
                  submitController.setAttempted();
                  if (!scope.$$phase) scope.$apply();
     
                  if (!formController.$valid) return false;
     
                  var doSubmit = function () {
     
                    submitController.submitInProgress = true;
                    if (!scope.$$phase) scope.$apply();
     
                    var returnPromise = $q.when(fn(scope, { $event: event }));
     
                    returnPromise.then(function (result) {
                      submitController.submitInProgress = false;
                      if (!scope.$$phase) scope.$apply();
                      
                      // This is a small hack.  We want the submitInProgress
                      // flag to be applied to the scope before we actually
                      // raise the submitComplete event. We do that by
                      // using angular's $timeout service which even without
                      // a timeout value specified will not fire until after
                      // the scope is digested.
                      $timeout(function() {
                        submitController.setSubmitComplete(true, result);
                      });
     
                    }, function (error) {
                      submitController.submitInProgress = false;
                      if (!scope.$$phase) scope.$apply();
                      $timeout(function() {
                        submitController.setSubmitComplete(false, error);
                      });
                    });
                  };
     
                  if (!scope.$$phase) {
                    scope.$apply(doSubmit);
                  } else {
                    doSubmit();
                    if (!scope.$$phase) scope.$apply();
                  }
                });
              }
            };
          }
        };
      });
