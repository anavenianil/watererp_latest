'use strict';

describe('Controller Tests', function() {

    describe('WorkflowRelationships Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkflowRelationships, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkflowRelationships = jasmine.createSpy('MockWorkflowRelationships');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkflowRelationships': MockWorkflowRelationships,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("WorkflowRelationshipsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:workflowRelationshipsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
