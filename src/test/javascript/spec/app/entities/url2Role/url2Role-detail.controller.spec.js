'use strict';

describe('Controller Tests', function() {

    describe('Url2Role Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockUrl2Role, MockUrl, MockAuthority;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockUrl2Role = jasmine.createSpy('MockUrl2Role');
            MockUrl = jasmine.createSpy('MockUrl');
            MockAuthority = jasmine.createSpy('MockAuthority');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Url2Role': MockUrl2Role,
                'Url': MockUrl,
                'Authority': MockAuthority
            };
            createController = function() {
                $injector.get('$controller')("Url2RoleDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:url2RoleUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
