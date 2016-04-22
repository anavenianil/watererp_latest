'use strict';

describe('Controller Tests', function() {

    describe('ApplicationTxn Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApplicationTxn, MockTariffCategoryMaster, MockMeterDetails, MockUser, MockDivisionMaster, MockStreetMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            MockMeterDetails = jasmine.createSpy('MockMeterDetails');
            MockUser = jasmine.createSpy('MockUser');
            MockDivisionMaster = jasmine.createSpy('MockDivisionMaster');
            MockStreetMaster = jasmine.createSpy('MockStreetMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApplicationTxn': MockApplicationTxn,
                'TariffCategoryMaster': MockTariffCategoryMaster,
                'MeterDetails': MockMeterDetails,
                'User': MockUser,
                'DivisionMaster': MockDivisionMaster,
                'StreetMaster': MockStreetMaster
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
