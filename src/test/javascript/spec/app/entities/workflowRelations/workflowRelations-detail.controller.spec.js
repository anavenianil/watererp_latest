'use strict';

describe('Controller Tests', function() {

    describe('WorkflowRelations Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkflowRelations, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkflowRelations = jasmine.createSpy('MockWorkflowRelations');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkflowRelations': MockWorkflowRelations,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("WorkflowRelationsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:workflowRelationsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
