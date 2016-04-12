'use strict';

describe('Controller Tests', function() {

    describe('BillRunDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBillRunDetails, MockBillFullDetails, MockBillRunMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBillRunDetails = jasmine.createSpy('MockBillRunDetails');
            MockBillFullDetails = jasmine.createSpy('MockBillFullDetails');
            MockBillRunMaster = jasmine.createSpy('MockBillRunMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BillRunDetails': MockBillRunDetails,
                'BillFullDetails': MockBillFullDetails,
                'BillRunMaster': MockBillRunMaster
            };
            createController = function() {
                $injector.get('$controller')("BillRunDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:billRunDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
