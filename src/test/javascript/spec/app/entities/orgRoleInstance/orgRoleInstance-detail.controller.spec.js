'use strict';

describe('Controller Tests', function() {

    describe('OrgRoleInstance Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOrgRoleInstance, MockStatusMaster, MockOrgRoleHierarchy, MockDepartmentsMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOrgRoleInstance = jasmine.createSpy('MockOrgRoleInstance');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            MockOrgRoleHierarchy = jasmine.createSpy('MockOrgRoleHierarchy');
            MockDepartmentsMaster = jasmine.createSpy('MockDepartmentsMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OrgRoleInstance': MockOrgRoleInstance,
                'StatusMaster': MockStatusMaster,
                'OrgRoleHierarchy': MockOrgRoleHierarchy,
                'DepartmentsMaster': MockDepartmentsMaster
            };
            createController = function() {
                $injector.get('$controller')("OrgRoleInstanceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:orgRoleInstanceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
