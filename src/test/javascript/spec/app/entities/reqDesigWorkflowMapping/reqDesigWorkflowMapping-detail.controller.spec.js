'use strict';

describe('Controller Tests', function() {

    describe('ReqDesigWorkflowMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockReqDesigWorkflowMapping, MockWorkflowMaster, MockRequestMaster, MockDesignationMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockReqDesigWorkflowMapping = jasmine.createSpy('MockReqDesigWorkflowMapping');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            MockDesignationMaster = jasmine.createSpy('MockDesignationMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ReqDesigWorkflowMapping': MockReqDesigWorkflowMapping,
                'WorkflowMaster': MockWorkflowMaster,
                'RequestMaster': MockRequestMaster,
                'DesignationMaster': MockDesignationMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("ReqDesigWorkflowMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:reqDesigWorkflowMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
