'use strict';

describe('Controller Tests', function() {

    describe('ComplaintTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockComplaintTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockComplaintTypeMaster = jasmine.createSpy('MockComplaintTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ComplaintTypeMaster': MockComplaintTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("ComplaintTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:complaintTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
