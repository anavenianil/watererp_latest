'use strict';

describe('Controller Tests', function() {

    describe('DepartmentTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDepartmentTypeMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDepartmentTypeMaster = jasmine.createSpy('MockDepartmentTypeMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DepartmentTypeMaster': MockDepartmentTypeMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("DepartmentTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:departmentTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
