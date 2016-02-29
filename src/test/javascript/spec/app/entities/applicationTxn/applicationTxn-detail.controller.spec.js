'use strict';

describe('Controller Tests', function() {

    describe('ApplicationTxn Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApplicationTxn, MockApplicationTypeMaster, MockConnectionTypeMaster, MockCtegoryMaster, MockPipeSizeMaster, MockSewerSize, MockFileNumber, MockCustomer;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockApplicationTypeMaster = jasmine.createSpy('MockApplicationTypeMaster');
            MockConnectionTypeMaster = jasmine.createSpy('MockConnectionTypeMaster');
            MockCtegoryMaster = jasmine.createSpy('MockCtegoryMaster');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            MockSewerSize = jasmine.createSpy('MockSewerSize');
            MockFileNumber = jasmine.createSpy('MockFileNumber');
            MockCustomer = jasmine.createSpy('MockCustomer');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApplicationTxn': MockApplicationTxn,
                'ApplicationTypeMaster': MockApplicationTypeMaster,
                'ConnectionTypeMaster': MockConnectionTypeMaster,
                'CtegoryMaster': MockCtegoryMaster,
                'PipeSizeMaster': MockPipeSizeMaster,
                'SewerSize': MockSewerSize,
                'FileNumber': MockFileNumber,
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
