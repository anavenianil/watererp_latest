'use strict';

describe('Controller Tests', function() {

    describe('BurstComplaint Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBurstComplaint, MockWaterLeakageComplaint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBurstComplaint = jasmine.createSpy('MockBurstComplaint');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BurstComplaint': MockBurstComplaint,
                'WaterLeakageComplaint': MockWaterLeakageComplaint
            };
            createController = function() {
                $injector.get('$controller')("BurstComplaintDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:burstComplaintUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
