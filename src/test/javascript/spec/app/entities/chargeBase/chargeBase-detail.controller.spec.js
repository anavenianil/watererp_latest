'use strict';

describe('Controller Tests', function() {

    describe('ChargeBase Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockChargeBase;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockChargeBase = jasmine.createSpy('MockChargeBase');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ChargeBase': MockChargeBase
            };
            createController = function() {
                $injector.get('$controller')("ChargeBaseDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:chargeBaseUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
