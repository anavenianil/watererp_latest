'use strict';

describe('Controller Tests', function() {

    describe('ExpenseDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockExpenseDetails, MockPaymentTypes, MockInstrumentIssuerMaster, MockCollectionTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockExpenseDetails = jasmine.createSpy('MockExpenseDetails');
            MockPaymentTypes = jasmine.createSpy('MockPaymentTypes');
            MockInstrumentIssuerMaster = jasmine.createSpy('MockInstrumentIssuerMaster');
            MockCollectionTypeMaster = jasmine.createSpy('MockCollectionTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ExpenseDetails': MockExpenseDetails,
                'PaymentTypes': MockPaymentTypes,
                'InstrumentIssuerMaster': MockInstrumentIssuerMaster,
                'CollectionTypeMaster': MockCollectionTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("ExpenseDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:expenseDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
