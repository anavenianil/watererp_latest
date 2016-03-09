'use strict';

describe('Controller Tests', function() {

    describe('MenuItem2Url Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMenuItem2Url, MockMenuItem, MockUrl;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMenuItem2Url = jasmine.createSpy('MockMenuItem2Url');
            MockMenuItem = jasmine.createSpy('MockMenuItem');
            MockUrl = jasmine.createSpy('MockUrl');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MenuItem2Url': MockMenuItem2Url,
                'MenuItem': MockMenuItem,
                'Url': MockUrl
            };
            createController = function() {
                $injector.get('$controller')("MenuItem2UrlDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:menuItem2UrlUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
