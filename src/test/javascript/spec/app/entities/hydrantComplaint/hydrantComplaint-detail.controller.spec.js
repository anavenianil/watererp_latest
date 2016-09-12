'use strict';

describe('Controller Tests', function() {

    describe('HydrantComplaint Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockHydrantComplaint, MockWaterLeakageComplaint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockHydrantComplaint = jasmine.createSpy('MockHydrantComplaint');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'HydrantComplaint': MockHydrantComplaint,
                'WaterLeakageComplaint': MockWaterLeakageComplaint
            };
            createController = function() {
                $injector.get('$controller')("HydrantComplaintDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:hydrantComplaintUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
