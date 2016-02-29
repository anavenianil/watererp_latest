'use strict';

describe('Controller Tests', function() {

    describe('WorkflowTxnDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkflowTxnDetails, MockRequestMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkflowTxnDetails = jasmine.createSpy('MockWorkflowTxnDetails');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkflowTxnDetails': MockWorkflowTxnDetails,
                'RequestMaster': MockRequestMaster
            };
            createController = function() {
                $injector.get('$controller')("WorkflowTxnDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:workflowTxnDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
