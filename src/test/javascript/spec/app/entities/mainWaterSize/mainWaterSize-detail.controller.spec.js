'use strict';

describe('Controller Tests', function() {

    describe('MainWaterSize Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMainWaterSize;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMainWaterSize = jasmine.createSpy('MockMainWaterSize');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MainWaterSize': MockMainWaterSize
            };
            createController = function() {
                $injector.get('$controller')("MainWaterSizeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:mainWaterSizeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
