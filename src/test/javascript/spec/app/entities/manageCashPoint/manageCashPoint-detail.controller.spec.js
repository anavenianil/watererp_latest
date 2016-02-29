'use strict';

describe('Controller Tests', function() {

    describe('ManageCashPoint Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockManageCashPoint, MockTransactionTypeMaster, MockCashBookMaster, MockPaymentTypes, MockFileNumber, MockCustomer;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockManageCashPoint = jasmine.createSpy('MockManageCashPoint');
            MockTransactionTypeMaster = jasmine.createSpy('MockTransactionTypeMaster');
            MockCashBookMaster = jasmine.createSpy('MockCashBookMaster');
            MockPaymentTypes = jasmine.createSpy('MockPaymentTypes');
            MockFileNumber = jasmine.createSpy('MockFileNumber');
            MockCustomer = jasmine.createSpy('MockCustomer');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ManageCashPoint': MockManageCashPoint,
                'TransactionTypeMaster': MockTransactionTypeMaster,
                'CashBookMaster': MockCashBookMaster,
                'PaymentTypes': MockPaymentTypes,
                'FileNumber': MockFileNumber,
                'Customer': MockCustomer
            };
            createController = function() {
                $injector.get('$controller')("ManageCashPointDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:manageCashPointUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
