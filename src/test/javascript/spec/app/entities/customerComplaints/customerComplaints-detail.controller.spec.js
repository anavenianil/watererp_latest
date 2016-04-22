'use strict';

describe('Controller Tests', function() {

    describe('CustomerComplaints Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCustomerComplaints, MockComplaintTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCustomerComplaints = jasmine.createSpy('MockCustomerComplaints');
            MockComplaintTypeMaster = jasmine.createSpy('MockComplaintTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CustomerComplaints': MockCustomerComplaints,
                'ComplaintTypeMaster': MockComplaintTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("CustomerComplaintsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:customerComplaintsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
