'use strict';

describe('Controller Tests', function() {

    describe('RequestMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRequestMaster, MockStatusMaster, MockModule;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRequestMaster = jasmine.createSpy('MockRequestMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            MockModule = jasmine.createSpy('MockModule');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RequestMaster': MockRequestMaster,
                'StatusMaster': MockStatusMaster,
                'Module': MockModule
            };
            createController = function() {
                $injector.get('$controller')("RequestMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:requestMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
