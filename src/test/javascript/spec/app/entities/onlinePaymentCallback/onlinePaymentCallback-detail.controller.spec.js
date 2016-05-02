'use strict';

describe('Controller Tests', function() {

    describe('OnlinePaymentCallback Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOnlinePaymentCallback, MockMerchantMaster, MockOnlinePaymentOrder;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOnlinePaymentCallback = jasmine.createSpy('MockOnlinePaymentCallback');
            MockMerchantMaster = jasmine.createSpy('MockMerchantMaster');
            MockOnlinePaymentOrder = jasmine.createSpy('MockOnlinePaymentOrder');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OnlinePaymentCallback': MockOnlinePaymentCallback,
                'MerchantMaster': MockMerchantMaster,
                'OnlinePaymentOrder': MockOnlinePaymentOrder
            };
            createController = function() {
                $injector.get('$controller')("OnlinePaymentCallbackDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:onlinePaymentCallbackUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
