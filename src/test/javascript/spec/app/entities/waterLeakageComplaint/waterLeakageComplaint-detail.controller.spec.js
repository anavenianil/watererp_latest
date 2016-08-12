'use strict';

describe('Controller Tests', function() {

    describe('WaterLeakageComplaint Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWaterLeakageComplaint, MockDivisionMaster, MockStreetMaster, MockJobCardItemRequirement;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWaterLeakageComplaint = jasmine.createSpy('MockWaterLeakageComplaint');
            MockDivisionMaster = jasmine.createSpy('MockDivisionMaster');
            MockStreetMaster = jasmine.createSpy('MockStreetMaster');
            MockJobCardItemRequirement = jasmine.createSpy('MockJobCardItemRequirement');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WaterLeakageComplaint': MockWaterLeakageComplaint,
                'DivisionMaster': MockDivisionMaster,
                'StreetMaster': MockStreetMaster,
                'JobCardItemRequirement': MockJobCardItemRequirement
            };
            createController = function() {
                $injector.get('$controller')("WaterLeakageComplaintDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:waterLeakageComplaintUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
