'use strict';

describe('Controller Tests', function() {

    describe('Workflow Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkflow, MockWorkflowMaster, MockWorkflowRelations, MockOrgRoleInstance, MockWorkflowRelationships, MockWorkflowStageMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkflow = jasmine.createSpy('MockWorkflow');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockWorkflowRelations = jasmine.createSpy('MockWorkflowRelations');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockWorkflowRelationships = jasmine.createSpy('MockWorkflowRelationships');
            MockWorkflowStageMaster = jasmine.createSpy('MockWorkflowStageMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Workflow': MockWorkflow,
                'WorkflowMaster': MockWorkflowMaster,
                'WorkflowRelations': MockWorkflowRelations,
                'OrgRoleInstance': MockOrgRoleInstance,
                'WorkflowRelationships': MockWorkflowRelationships,
                'WorkflowStageMaster': MockWorkflowStageMaster
            };
            createController = function() {
                $injector.get('$controller')("WorkflowDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:workflowUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
