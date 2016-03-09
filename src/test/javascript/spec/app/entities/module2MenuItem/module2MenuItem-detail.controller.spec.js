'use strict';

describe('Controller Tests', function() {

    describe('Module2MenuItem Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockModule2MenuItem, MockModule, MockMenuItem;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockModule2MenuItem = jasmine.createSpy('MockModule2MenuItem');
            MockModule = jasmine.createSpy('MockModule');
            MockMenuItem = jasmine.createSpy('MockMenuItem');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Module2MenuItem': MockModule2MenuItem,
                'Module': MockModule,
                'MenuItem': MockMenuItem
            };
            createController = function() {
                $injector.get('$controller')("Module2MenuItemDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:module2MenuItemUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
