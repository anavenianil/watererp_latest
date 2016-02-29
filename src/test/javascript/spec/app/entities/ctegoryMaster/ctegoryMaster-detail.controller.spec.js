'use strict';

describe('Controller Tests', function() {

    describe('CtegoryMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCtegoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCtegoryMaster = jasmine.createSpy('MockCtegoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CtegoryMaster': MockCtegoryMaster
            };
            createController = function() {
                $injector.get('$controller')("CtegoryMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:ctegoryMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
