'use strict';

describe('Controller Tests', function() {

    describe('OnlinePaymentResponse Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOnlinePaymentResponse, MockOnlinePaymentOrder;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOnlinePaymentResponse = jasmine.createSpy('MockOnlinePaymentResponse');
            MockOnlinePaymentOrder = jasmine.createSpy('MockOnlinePaymentOrder');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OnlinePaymentResponse': MockOnlinePaymentResponse,
                'OnlinePaymentOrder': MockOnlinePaymentOrder
            };
            createController = function() {
                $injector.get('$controller')("OnlinePaymentResponseDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:onlinePaymentResponseUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
