'use strict';

describe('Controller Tests', function() {

    describe('MeterChange Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMeterChange, MockCustDetails, MockMeterDetails, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMeterChange = jasmine.createSpy('MockMeterChange');
            MockCustDetails = jasmine.createSpy('MockCustDetails');
            MockMeterDetails = jasmine.createSpy('MockMeterDetails');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MeterChange': MockMeterChange,
                'CustDetails': MockCustDetails,
                'MeterDetails': MockMeterDetails,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("MeterChangeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:meterChangeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
