'use strict';

describe('Controller Tests', function() {

    describe('OrgRoleHierarchy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOrgRoleHierarchy, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOrgRoleHierarchy = jasmine.createSpy('MockOrgRoleHierarchy');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OrgRoleHierarchy': MockOrgRoleHierarchy,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("OrgRoleHierarchyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:orgRoleHierarchyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
