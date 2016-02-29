'use strict';

describe('Controller Tests', function() {

    describe('EmpMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockEmpMaster, MockUser, MockOrgRoleInstance, MockDesignationMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockEmpMaster = jasmine.createSpy('MockEmpMaster');
            MockUser = jasmine.createSpy('MockUser');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockDesignationMaster = jasmine.createSpy('MockDesignationMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'EmpMaster': MockEmpMaster,
                'User': MockUser,
                'OrgRoleInstance': MockOrgRoleInstance,
                'DesignationMaster': MockDesignationMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("EmpMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:empMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
