'use strict';

describe('Controller Tests', function() {

    describe('BillOfMaterial Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBillOfMaterial, MockApplicationTxn, MockPaymentTypes;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBillOfMaterial = jasmine.createSpy('MockBillOfMaterial');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockPaymentTypes = jasmine.createSpy('MockPaymentTypes');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BillOfMaterial': MockBillOfMaterial,
                'ApplicationTxn': MockApplicationTxn,
                'PaymentTypes': MockPaymentTypes
            };
            createController = function() {
                $injector.get('$controller')("BillOfMaterialDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:billOfMaterialUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
