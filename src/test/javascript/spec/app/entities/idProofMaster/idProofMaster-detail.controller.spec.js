'use strict';

describe('Controller Tests', function() {

    describe('IdProofMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockIdProofMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockIdProofMaster = jasmine.createSpy('MockIdProofMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'IdProofMaster': MockIdProofMaster
            };
            createController = function() {
                $injector.get('$controller')("IdProofMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:idProofMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
