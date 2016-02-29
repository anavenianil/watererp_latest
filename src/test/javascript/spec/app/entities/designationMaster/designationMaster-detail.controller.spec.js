'use strict';

describe('Controller Tests', function() {

    describe('DesignationMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDesignationMaster, MockStatusMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDesignationMaster = jasmine.createSpy('MockDesignationMaster');
            MockStatusMaster = jasmine.createSpy('MockStatusMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DesignationMaster': MockDesignationMaster,
                'StatusMaster': MockStatusMaster
            };
            createController = function() {
                $injector.get('$controller')("DesignationMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:designationMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
