'use strict';

describe('Controller Tests', function() {

    describe('ItemCodeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemCodeMaster, MockItemCategoryMaster, MockItemSubCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemCodeMaster = jasmine.createSpy('MockItemCodeMaster');
            MockItemCategoryMaster = jasmine.createSpy('MockItemCategoryMaster');
            MockItemSubCategoryMaster = jasmine.createSpy('MockItemSubCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemCodeMaster': MockItemCodeMaster,
                'ItemCategoryMaster': MockItemCategoryMaster,
                'ItemSubCategoryMaster': MockItemSubCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("ItemCodeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemCodeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
