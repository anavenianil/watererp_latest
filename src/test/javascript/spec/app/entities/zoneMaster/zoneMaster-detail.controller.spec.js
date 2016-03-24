'use strict';

describe('Controller Tests', function() {

    describe('ZoneMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockZoneMaster, MockDivisionMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockZoneMaster = jasmine.createSpy('MockZoneMaster');
            MockDivisionMaster = jasmine.createSpy('MockDivisionMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ZoneMaster': MockZoneMaster,
                'DivisionMaster': MockDivisionMaster
            };
            createController = function() {
                $injector.get('$controller')("ZoneMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:zoneMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
