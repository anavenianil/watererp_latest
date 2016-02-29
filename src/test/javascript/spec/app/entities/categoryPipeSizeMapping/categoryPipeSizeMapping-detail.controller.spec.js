'use strict';

describe('Controller Tests', function() {

    describe('CategoryPipeSizeMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCategoryPipeSizeMapping, MockCtegoryMaster, MockPipeSizeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCategoryPipeSizeMapping = jasmine.createSpy('MockCategoryPipeSizeMapping');
            MockCtegoryMaster = jasmine.createSpy('MockCtegoryMaster');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CategoryPipeSizeMapping': MockCategoryPipeSizeMapping,
                'CtegoryMaster': MockCtegoryMaster,
                'PipeSizeMaster': MockPipeSizeMaster
            };
            createController = function() {
                $injector.get('$controller')("CategoryPipeSizeMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:categoryPipeSizeMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
