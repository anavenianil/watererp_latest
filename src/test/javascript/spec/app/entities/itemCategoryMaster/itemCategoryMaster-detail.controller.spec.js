'use strict';

describe('Controller Tests', function() {

    describe('ItemCategoryMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemCategoryMaster = jasmine.createSpy('MockItemCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemCategoryMaster': MockItemCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("ItemCategoryMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemCategoryMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
