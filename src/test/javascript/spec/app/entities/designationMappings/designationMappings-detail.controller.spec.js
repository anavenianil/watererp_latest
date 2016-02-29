'use strict';

describe('Controller Tests', function() {

    describe('DesignationMappings Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDesignationMappings, MockDesigCategoryMaster, MockSubDesigCategoryMaster, MockDesignationMaster, MockGroupMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDesignationMappings = jasmine.createSpy('MockDesignationMappings');
            MockDesigCategoryMaster = jasmine.createSpy('MockDesigCategoryMaster');
            MockSubDesigCategoryMaster = jasmine.createSpy('MockSubDesigCategoryMaster');
            MockDesignationMaster = jasmine.createSpy('MockDesignationMaster');
            MockGroupMaster = jasmine.createSpy('MockGroupMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DesignationMappings': MockDesignationMappings,
                'DesigCategoryMaster': MockDesigCategoryMaster,
                'SubDesigCategoryMaster': MockSubDesigCategoryMaster,
                'DesignationMaster': MockDesignationMaster,
                'GroupMaster': MockGroupMaster
            };
            createController = function() {
                $injector.get('$controller')("DesignationMappingsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:designationMappingsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
