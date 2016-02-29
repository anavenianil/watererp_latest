'use strict';

describe('Controller Tests', function() {

    describe('ConnectionTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockConnectionTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockConnectionTypeMaster = jasmine.createSpy('MockConnectionTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ConnectionTypeMaster': MockConnectionTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("ConnectionTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:connectionTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
