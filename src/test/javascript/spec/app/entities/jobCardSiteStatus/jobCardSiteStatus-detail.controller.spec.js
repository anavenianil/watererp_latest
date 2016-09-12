'use strict';

describe('Controller Tests', function() {

    describe('JobCardSiteStatus Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockJobCardSiteStatus, MockWaterLeakageComplaint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockJobCardSiteStatus = jasmine.createSpy('MockJobCardSiteStatus');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'JobCardSiteStatus': MockJobCardSiteStatus,
                'WaterLeakageComplaint': MockWaterLeakageComplaint
            };
            createController = function() {
                $injector.get('$controller')("JobCardSiteStatusDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:jobCardSiteStatusUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
