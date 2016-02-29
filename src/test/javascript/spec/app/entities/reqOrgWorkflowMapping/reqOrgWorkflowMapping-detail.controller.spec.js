'use strict';

describe('Controller Tests', function() {

    describe('ReqOrgWorkflowMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockReqOrgWorkflowMapping, MockWorkflowMaster, MockRequestMaster, MockOrgRoleInstance, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockReqOrgWorkflowMapping = jasmine.createSpy('MockReqOrgWorkflowMapping');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ReqOrgWorkflowMapping': MockReqOrgWorkflowMapping,
                'WorkflowMaster': MockWorkflowMaster,
                'RequestMaster': MockRequestMaster,
                'OrgRoleInstance': MockOrgRoleInstance,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("ReqOrgWorkflowMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:reqOrgWorkflowMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
