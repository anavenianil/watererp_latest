'use strict';

describe('Controller Tests', function() {

    describe('SewerageRequest Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockSewerageRequest, MockTariffCategoryMaster, MockReceipt;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockSewerageRequest = jasmine.createSpy('MockSewerageRequest');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            MockReceipt = jasmine.createSpy('MockReceipt');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'SewerageRequest': MockSewerageRequest,
                'TariffCategoryMaster': MockTariffCategoryMaster,
                'Receipt': MockReceipt
            };
            createController = function() {
                $injector.get('$controller')("SewerageRequestDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:sewerageRequestUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
