'use strict';

describe('Controller Tests', function() {

    describe('MainSewerageSize Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMainSewerageSize;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMainSewerageSize = jasmine.createSpy('MockMainSewerageSize');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MainSewerageSize': MockMainSewerageSize
            };
            createController = function() {
                $injector.get('$controller')("MainSewerageSizeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:mainSewerageSizeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
