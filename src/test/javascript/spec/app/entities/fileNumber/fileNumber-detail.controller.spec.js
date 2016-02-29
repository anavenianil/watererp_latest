'use strict';

describe('Controller Tests', function() {

    describe('FileNumber Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFileNumber;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFileNumber = jasmine.createSpy('MockFileNumber');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'FileNumber': MockFileNumber
            };
            createController = function() {
                $injector.get('$controller')("FileNumberDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:fileNumberUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
