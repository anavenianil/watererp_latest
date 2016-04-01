'use strict';

describe('Controller Tests', function() {

    describe('TariffMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTariffMaster, MockTariffCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTariffMaster = jasmine.createSpy('MockTariffMaster');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TariffMaster': MockTariffMaster,
                'TariffCategoryMaster': MockTariffCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("TariffMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:tariffMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
