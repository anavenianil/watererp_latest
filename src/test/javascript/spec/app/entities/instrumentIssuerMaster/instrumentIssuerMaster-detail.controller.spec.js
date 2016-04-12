'use strict';

describe('Controller Tests', function() {

    describe('InstrumentIssuerMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockInstrumentIssuerMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockInstrumentIssuerMaster = jasmine.createSpy('MockInstrumentIssuerMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'InstrumentIssuerMaster': MockInstrumentIssuerMaster
            };
            createController = function() {
                $injector.get('$controller')("InstrumentIssuerMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:instrumentIssuerMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
