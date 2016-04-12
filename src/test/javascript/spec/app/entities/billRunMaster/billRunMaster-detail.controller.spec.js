'use strict';

describe('Controller Tests', function() {

    describe('BillRunMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBillRunMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBillRunMaster = jasmine.createSpy('MockBillRunMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BillRunMaster': MockBillRunMaster
            };
            createController = function() {
                $injector.get('$controller')("BillRunMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:billRunMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
