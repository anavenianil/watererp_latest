'use strict';

describe('Controller Tests', function() {

    describe('JobCardItemRequirement Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockJobCardItemRequirement, MockMaterialMaster, MockUom, MockWaterLeakageComplaint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockJobCardItemRequirement = jasmine.createSpy('MockJobCardItemRequirement');
            MockMaterialMaster = jasmine.createSpy('MockMaterialMaster');
            MockUom = jasmine.createSpy('MockUom');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'JobCardItemRequirement': MockJobCardItemRequirement,
                'MaterialMaster': MockMaterialMaster,
                'Uom': MockUom,
                'WaterLeakageComplaint': MockWaterLeakageComplaint
            };
            createController = function() {
                $injector.get('$controller')("JobCardItemRequirementDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:jobCardItemRequirementUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
