'use strict';

describe('Controller Tests', function() {

    describe('PercentageMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPercentageMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPercentageMaster = jasmine.createSpy('MockPercentageMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'PercentageMaster': MockPercentageMaster
            };
            createController = function() {
                $injector.get('$controller')("PercentageMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:percentageMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
