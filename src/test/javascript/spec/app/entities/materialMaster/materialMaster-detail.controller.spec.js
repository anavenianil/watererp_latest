'use strict';

describe('Controller Tests', function() {

    describe('MaterialMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMaterialMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMaterialMaster = jasmine.createSpy('MockMaterialMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MaterialMaster': MockMaterialMaster
            };
            createController = function() {
                $injector.get('$controller')("MaterialMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:materialMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
