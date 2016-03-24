'use strict';

describe('Controller Tests', function() {

    describe('DivisionMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDivisionMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDivisionMaster = jasmine.createSpy('MockDivisionMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DivisionMaster': MockDivisionMaster
            };
            createController = function() {
                $injector.get('$controller')("DivisionMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:divisionMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
