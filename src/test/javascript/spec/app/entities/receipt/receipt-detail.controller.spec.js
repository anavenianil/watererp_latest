'use strict';

describe('Controller Tests', function() {

    describe('Receipt Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockReceipt, MockApplicationTxn, MockPaymentTypes;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockReceipt = jasmine.createSpy('MockReceipt');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockPaymentTypes = jasmine.createSpy('MockPaymentTypes');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Receipt': MockReceipt,
                'ApplicationTxn': MockApplicationTxn,
                'PaymentTypes': MockPaymentTypes
            };
            createController = function() {
                $injector.get('$controller')("ReceiptDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:receiptUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
