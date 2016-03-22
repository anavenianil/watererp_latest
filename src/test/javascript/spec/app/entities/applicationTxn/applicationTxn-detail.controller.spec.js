'use strict';

describe('Controller Tests', function() {

    describe('ApplicationTxn Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApplicationTxn, MockApplicationTypeMaster, MockConnectionTypeMaster, MockCategoryMaster, MockPipeSizeMaster, MockSewerSize, MockCustomer;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockApplicationTypeMaster = jasmine.createSpy('MockApplicationTypeMaster');
            MockConnectionTypeMaster = jasmine.createSpy('MockConnectionTypeMaster');
            MockCategoryMaster = jasmine.createSpy('MockCategoryMaster');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            MockSewerSize = jasmine.createSpy('MockSewerSize');
            MockCustomer = jasmine.createSpy('MockCustomer');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApplicationTxn': MockApplicationTxn,
                'ApplicationTypeMaster': MockApplicationTypeMaster,
                'ConnectionTypeMaster': MockConnectionTypeMaster,
                'CategoryMaster': MockCategoryMaster,
                'PipeSizeMaster': MockPipeSizeMaster,
                'SewerSize': MockSewerSize,
                'Customer': MockCustomer
            };
            createController = function() {
                $injector.get('$controller')("ApplicationTxnDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:applicationTxnUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
