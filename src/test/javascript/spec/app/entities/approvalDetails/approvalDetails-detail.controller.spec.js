'use strict';

describe('Controller Tests', function() {

    describe('ApprovalDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApprovalDetails, MockCustomer, MockFeasibilityStatus, MockDesignationMaster, MockApplicationTxn;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApprovalDetails = jasmine.createSpy('MockApprovalDetails');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockFeasibilityStatus = jasmine.createSpy('MockFeasibilityStatus');
            MockDesignationMaster = jasmine.createSpy('MockDesignationMaster');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApprovalDetails': MockApprovalDetails,
                'Customer': MockCustomer,
                'FeasibilityStatus': MockFeasibilityStatus,
                'DesignationMaster': MockDesignationMaster,
                'ApplicationTxn': MockApplicationTxn
            };
            createController = function() {
                $injector.get('$controller')("ApprovalDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:approvalDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
