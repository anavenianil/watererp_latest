'use strict';

describe('Controller Tests', function() {

    describe('WorkflowMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkflowMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkflowMaster': MockWorkflowMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("WorkflowMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:workflowMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
