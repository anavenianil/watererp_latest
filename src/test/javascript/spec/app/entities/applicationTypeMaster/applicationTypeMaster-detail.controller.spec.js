'use strict';

describe('Controller Tests', function() {

    describe('ApplicationTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockApplicationTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockApplicationTypeMaster = jasmine.createSpy('MockApplicationTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ApplicationTypeMaster': MockApplicationTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("ApplicationTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:applicationTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
