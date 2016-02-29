'use strict';

describe('Controller Tests', function() {

    describe('FeasibilityStatus Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFeasibilityStatus;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFeasibilityStatus = jasmine.createSpy('MockFeasibilityStatus');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'FeasibilityStatus': MockFeasibilityStatus
            };
            createController = function() {
                $injector.get('$controller')("FeasibilityStatusDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:feasibilityStatusUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
