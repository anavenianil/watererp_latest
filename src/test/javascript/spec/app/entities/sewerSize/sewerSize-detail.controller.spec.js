'use strict';

describe('Controller Tests', function() {

    describe('SewerSize Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockSewerSize;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockSewerSize = jasmine.createSpy('MockSewerSize');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'SewerSize': MockSewerSize
            };
            createController = function() {
                $injector.get('$controller')("SewerSizeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:sewerSizeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
