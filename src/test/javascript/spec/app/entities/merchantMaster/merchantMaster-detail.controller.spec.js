'use strict';

describe('Controller Tests', function() {

    describe('MerchantMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMerchantMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMerchantMaster = jasmine.createSpy('MockMerchantMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MerchantMaster': MockMerchantMaster
            };
            createController = function() {
                $injector.get('$controller')("MerchantMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:merchantMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
