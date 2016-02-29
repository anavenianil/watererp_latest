'use strict';

describe('Controller Tests', function() {

    describe('RoleWorkflowMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRoleWorkflowMapping, MockStatusMaster, MockOrgRoleInstance, MockWorkflowMaster, MockRequestMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRoleWorkflowMapping = jasmine.createSpy('MockRoleWorkflowMapping');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RoleWorkflowMapping': MockRoleWorkflowMapping,
                'StatusMaster': MockStatusMaster,
                'OrgRoleInstance': MockOrgRoleInstance,
                'WorkflowMaster': MockWorkflowMaster,
                'RequestMaster': MockRequestMaster
            };
            createController = function() {
                $injector.get('$controller')("RoleWorkflowMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:roleWorkflowMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
