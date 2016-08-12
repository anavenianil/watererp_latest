'use strict';

describe('Controller Tests', function() {

    describe('ValveComplaint Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockValveComplaint, MockWaterLeakageComplaint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockValveComplaint = jasmine.createSpy('MockValveComplaint');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ValveComplaint': MockValveComplaint,
                'WaterLeakageComplaint': MockWaterLeakageComplaint
            };
            createController = function() {
                $injector.get('$controller')("ValveComplaintDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:valveComplaintUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
