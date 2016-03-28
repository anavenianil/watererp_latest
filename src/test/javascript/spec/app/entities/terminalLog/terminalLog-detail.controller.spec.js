'use strict';

describe('Controller Tests', function() {

    describe('TerminalLog Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTerminalLog;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTerminalLog = jasmine.createSpy('MockTerminalLog');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TerminalLog': MockTerminalLog
            };
            createController = function() {
                $injector.get('$controller')("TerminalLogDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:terminalLogUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
