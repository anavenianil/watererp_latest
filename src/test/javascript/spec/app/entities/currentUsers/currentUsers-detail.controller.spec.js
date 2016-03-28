'use strict';

describe('Controller Tests', function() {

    describe('CurrentUsers Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCurrentUsers;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCurrentUsers = jasmine.createSpy('MockCurrentUsers');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CurrentUsers': MockCurrentUsers
            };
            createController = function() {
                $injector.get('$controller')("CurrentUsersDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:currentUsersUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
