'use strict';

describe('Controller Tests', function() {

    describe('DepartmentsHierarchy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDepartmentsHierarchy, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDepartmentsHierarchy = jasmine.createSpy('MockDepartmentsHierarchy');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DepartmentsHierarchy': MockDepartmentsHierarchy,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("DepartmentsHierarchyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:departmentsHierarchyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
