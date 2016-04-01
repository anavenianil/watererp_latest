'use strict';

describe('Controller Tests', function() {

    describe('TariffCharges Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTariffCharges, MockTariffMaster, MockTariffTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTariffCharges = jasmine.createSpy('MockTariffCharges');
            MockTariffMaster = jasmine.createSpy('MockTariffMaster');
            MockTariffTypeMaster = jasmine.createSpy('MockTariffTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TariffCharges': MockTariffCharges,
                'TariffMaster': MockTariffMaster,
                'TariffTypeMaster': MockTariffTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("TariffChargesDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:tariffChargesUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
