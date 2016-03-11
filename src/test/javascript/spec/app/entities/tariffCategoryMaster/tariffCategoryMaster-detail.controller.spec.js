'use strict';

describe('Controller Tests', function() {

    describe('TariffCategoryMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTariffCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TariffCategoryMaster': MockTariffCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("TariffCategoryMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:tariffCategoryMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
