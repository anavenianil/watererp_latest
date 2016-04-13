'use strict';

describe('Controller Tests', function() {

    describe('ApplicationTxn Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApplicationTxn, MockCategoryMaster, MockCustomer, MockMeterDetails;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockCategoryMaster = jasmine.createSpy('MockCategoryMaster');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockMeterDetails = jasmine.createSpy('MockMeterDetails');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApplicationTxn': MockApplicationTxn,
                'CategoryMaster': MockCategoryMaster,
                'Customer': MockCustomer,
                'MeterDetails': MockMeterDetails
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
