'use strict';

describe('Controller Tests', function() {

    describe('ReAllotment Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockReAllotment, MockFileNumber, MockCustomer, MockFeasibilityStatus;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockReAllotment = jasmine.createSpy('MockReAllotment');
            MockFileNumber = jasmine.createSpy('MockFileNumber');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockFeasibilityStatus = jasmine.createSpy('MockFeasibilityStatus');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ReAllotment': MockReAllotment,
                'FileNumber': MockFileNumber,
                'Customer': MockCustomer,
                'FeasibilityStatus': MockFeasibilityStatus
            };
            createController = function() {
                $injector.get('$controller')("ReAllotmentDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:reAllotmentUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
