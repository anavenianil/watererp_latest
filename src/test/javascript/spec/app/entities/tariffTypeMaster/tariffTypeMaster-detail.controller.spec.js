'use strict';

describe('Controller Tests', function() {

    describe('TariffTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTariffTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTariffTypeMaster = jasmine.createSpy('MockTariffTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TariffTypeMaster': MockTariffTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("TariffTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:tariffTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
