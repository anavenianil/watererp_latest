'use strict';

describe('Controller Tests', function() {

    describe('MakeOfPipe Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMakeOfPipe;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMakeOfPipe = jasmine.createSpy('MockMakeOfPipe');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MakeOfPipe': MockMakeOfPipe
            };
            createController = function() {
                $injector.get('$controller')("MakeOfPipeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:makeOfPipeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
