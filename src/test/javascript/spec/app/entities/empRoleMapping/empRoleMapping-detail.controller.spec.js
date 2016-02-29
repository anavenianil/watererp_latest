'use strict';

describe('Controller Tests', function() {

    describe('EmpRoleMapping Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockEmpRoleMapping, MockUser, MockOrgRoleInstance, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockEmpRoleMapping = jasmine.createSpy('MockEmpRoleMapping');
            MockUser = jasmine.createSpy('MockUser');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'EmpRoleMapping': MockEmpRoleMapping,
                'User': MockUser,
                'OrgRoleInstance': MockOrgRoleInstance,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("EmpRoleMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:empRoleMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
