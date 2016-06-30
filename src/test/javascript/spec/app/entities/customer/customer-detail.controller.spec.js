'use strict';

describe('Controller Tests', function() {

    describe('Customer Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCustomer, MockTariffCategoryMaster, MockIdProofMaster, MockPipeSizeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            MockIdProofMaster = jasmine.createSpy('MockIdProofMaster');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Customer': MockCustomer,
                'TariffCategoryMaster': MockTariffCategoryMaster,
                'IdProofMaster': MockIdProofMaster,
                'PipeSizeMaster': MockPipeSizeMaster
            };
            createController = function() {
                $injector.get('$controller')("CustomerDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:customerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
