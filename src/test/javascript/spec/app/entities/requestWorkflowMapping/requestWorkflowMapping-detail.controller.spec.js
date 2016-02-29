'use strict';

describe('Controller Tests', function() {

    describe('RequestWorkflowMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRequestWorkflowMapping, MockStatusMaster, MockWorkflowMaster, MockRequestMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRequestWorkflowMapping = jasmine.createSpy('MockRequestWorkflowMapping');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RequestWorkflowMapping': MockRequestWorkflowMapping,
                'StatusMaster': MockStatusMaster,
                'WorkflowMaster': MockWorkflowMaster,
                'RequestMaster': MockRequestMaster
            };
            createController = function() {
                $injector.get('$controller')("RequestWorkflowMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:requestWorkflowMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
