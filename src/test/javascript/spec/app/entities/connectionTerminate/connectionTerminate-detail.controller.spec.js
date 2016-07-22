'use strict';

describe('Controller Tests', function() {

    describe('ConnectionTerminate Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockConnectionTerminate, MockMeterDetails;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockConnectionTerminate = jasmine.createSpy('MockConnectionTerminate');
            MockMeterDetails = jasmine.createSpy('MockMeterDetails');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ConnectionTerminate': MockConnectionTerminate,
                'MeterDetails': MockMeterDetails
            };
            createController = function() {
                $injector.get('$controller')("ConnectionTerminateDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:connectionTerminateUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
