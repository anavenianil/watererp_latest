'use strict';

describe('Controller Tests', function() {

    describe('StreetMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockStreetMaster, MockZoneMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockStreetMaster = jasmine.createSpy('MockStreetMaster');
            MockZoneMaster = jasmine.createSpy('MockZoneMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'StreetMaster': MockStreetMaster,
                'ZoneMaster': MockZoneMaster
            };
            createController = function() {
                $injector.get('$controller')("StreetMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:streetMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
