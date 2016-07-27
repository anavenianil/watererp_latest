'use strict';

describe('Controller Tests', function() {

    describe('OnlinePaymentOrder Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOnlinePaymentOrder, MockMerchantMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOnlinePaymentOrder = jasmine.createSpy('MockOnlinePaymentOrder');
            MockMerchantMaster = jasmine.createSpy('MockMerchantMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OnlinePaymentOrder': MockOnlinePaymentOrder,
                'MerchantMaster': MockMerchantMaster
            };
            createController = function() {
                $injector.get('$controller')("OnlinePaymentOrderDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:onlinePaymentOrderUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
