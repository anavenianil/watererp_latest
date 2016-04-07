'use strict';

describe('Controller Tests', function() {

    describe('CollDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCollDetails, MockPaymentTypes, MockInstrumentIssuerMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCollDetails = jasmine.createSpy('MockCollDetails');
            MockPaymentTypes = jasmine.createSpy('MockPaymentTypes');
            MockInstrumentIssuerMaster = jasmine.createSpy('MockInstrumentIssuerMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CollDetails': MockCollDetails,
                'PaymentTypes': MockPaymentTypes,
                'InstrumentIssuerMaster': MockInstrumentIssuerMaster
            };
            createController = function() {
                $injector.get('$controller')("CollDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:collDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
