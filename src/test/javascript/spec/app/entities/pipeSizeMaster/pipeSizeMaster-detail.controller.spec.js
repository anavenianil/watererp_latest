'use strict';

describe('Controller Tests', function() {

    describe('PipeSizeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPipeSizeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'PipeSizeMaster': MockPipeSizeMaster
            };
            createController = function() {
                $injector.get('$controller')("PipeSizeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:pipeSizeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
