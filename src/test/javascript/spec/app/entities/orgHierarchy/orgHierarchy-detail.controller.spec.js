'use strict';

describe('Controller Tests', function() {

    describe('OrgHierarchy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockOrgHierarchy, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockOrgHierarchy = jasmine.createSpy('MockOrgHierarchy');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'OrgHierarchy': MockOrgHierarchy,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("OrgHierarchyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:orgHierarchyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
