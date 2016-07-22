'use strict';

describe('Controller Tests', function() {

    describe('ReversalDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockReversalDetails, MockCollDetails, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockReversalDetails = jasmine.createSpy('MockReversalDetails');
            MockCollDetails = jasmine.createSpy('MockCollDetails');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ReversalDetails': MockReversalDetails,
                'CollDetails': MockCollDetails,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("ReversalDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:reversalDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
