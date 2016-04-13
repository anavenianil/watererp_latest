'use strict';

describe('Controller Tests', function() {

    describe('CollectionTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCollectionTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCollectionTypeMaster = jasmine.createSpy('MockCollectionTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CollectionTypeMaster': MockCollectionTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("CollectionTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:collectionTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
