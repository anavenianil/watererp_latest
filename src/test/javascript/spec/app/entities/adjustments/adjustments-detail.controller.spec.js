'use strict';

describe('Controller Tests', function() {

    describe('Adjustments Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockAdjustments, MockCustDetails, MockBillFullDetails, MockTransactionTypeMaster, MockUser, MockComplaintTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockAdjustments = jasmine.createSpy('MockAdjustments');
            MockCustDetails = jasmine.createSpy('MockCustDetails');
            MockBillFullDetails = jasmine.createSpy('MockBillFullDetails');
            MockTransactionTypeMaster = jasmine.createSpy('MockTransactionTypeMaster');
            MockUser = jasmine.createSpy('MockUser');
            MockComplaintTypeMaster = jasmine.createSpy('MockComplaintTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Adjustments': MockAdjustments,
                'CustDetails': MockCustDetails,
                'BillFullDetails': MockBillFullDetails,
                'TransactionTypeMaster': MockTransactionTypeMaster,
                'User': MockUser,
                'ComplaintTypeMaster': MockComplaintTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("AdjustmentsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:adjustmentsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
