'use strict';

describe('Controller Tests', function() {

    describe('InvoicePayments Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockInvoicePayments, MockCustDetails, MockBillFullDetails, MockCollDetails;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockInvoicePayments = jasmine.createSpy('MockInvoicePayments');
            MockCustDetails = jasmine.createSpy('MockCustDetails');
            MockBillFullDetails = jasmine.createSpy('MockBillFullDetails');
            MockCollDetails = jasmine.createSpy('MockCollDetails');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'InvoicePayments': MockInvoicePayments,
                'CustDetails': MockCustDetails,
                'BillFullDetails': MockBillFullDetails,
                'CollDetails': MockCollDetails
            };
            createController = function() {
                $injector.get('$controller')("InvoicePaymentsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:invoicePaymentsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
