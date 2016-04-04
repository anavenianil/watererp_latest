'use strict';

describe('Controller Tests', function() {

    describe('BillFullDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBillFullDetails;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBillFullDetails = jasmine.createSpy('MockBillFullDetails');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BillFullDetails': MockBillFullDetails
            };
            createController = function() {
                $injector.get('$controller')("BillFullDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:billFullDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
