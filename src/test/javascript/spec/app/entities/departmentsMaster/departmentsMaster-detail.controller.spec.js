'use strict';

describe('Controller Tests', function() {

    describe('DepartmentsMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDepartmentsMaster, MockDepartmentsHierarchy, MockDepartmentTypeMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDepartmentsMaster = jasmine.createSpy('MockDepartmentsMaster');
            MockDepartmentsHierarchy = jasmine.createSpy('MockDepartmentsHierarchy');
            MockDepartmentTypeMaster = jasmine.createSpy('MockDepartmentTypeMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DepartmentsMaster': MockDepartmentsMaster,
                'DepartmentsHierarchy': MockDepartmentsHierarchy,
                'DepartmentTypeMaster': MockDepartmentTypeMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("DepartmentsMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:departmentsMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
