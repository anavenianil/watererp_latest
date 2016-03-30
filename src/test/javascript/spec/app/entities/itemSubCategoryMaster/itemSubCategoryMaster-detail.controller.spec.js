'use strict';

describe('Controller Tests', function() {

    describe('ItemSubCategoryMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemSubCategoryMaster, MockItemCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemSubCategoryMaster = jasmine.createSpy('MockItemSubCategoryMaster');
            MockItemCategoryMaster = jasmine.createSpy('MockItemCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemSubCategoryMaster': MockItemSubCategoryMaster,
                'ItemCategoryMaster': MockItemCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("ItemSubCategoryMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemSubCategoryMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
