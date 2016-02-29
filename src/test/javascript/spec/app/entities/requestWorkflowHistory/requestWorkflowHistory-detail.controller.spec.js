'use strict';

describe('Controller Tests', function() {

    describe('RequestWorkflowHistory Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRequestWorkflowHistory, MockUser, MockStatusMaster, MockRequestMaster, MockWorkflowMaster, MockWorkflowStageMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRequestWorkflowHistory = jasmine.createSpy('MockRequestWorkflowHistory');
            MockUser = jasmine.createSpy('MockUser');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            MockWorkflowMaster = jasmine.createSpy('MockWorkflowMaster');
            MockWorkflowStageMaster = jasmine.createSpy('MockWorkflowStageMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RequestWorkflowHistory': MockRequestWorkflowHistory,
                'User': MockUser,
                'StatusMaster': MockStatusMaster,
                'RequestMaster': MockRequestMaster,
                'WorkflowMaster': MockWorkflowMaster,
                'WorkflowStageMaster': MockWorkflowStageMaster
            };
            createController = function() {
                $injector.get('$controller')("RequestWorkflowHistoryDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:requestWorkflowHistoryUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
