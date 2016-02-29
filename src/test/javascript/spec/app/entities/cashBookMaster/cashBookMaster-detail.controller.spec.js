'use strict';

describe('Controller Tests', function() {

    describe('CashBookMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCashBookMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCashBookMaster = jasmine.createSpy('MockCashBookMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CashBookMaster': MockCashBookMaster
            };
            createController = function() {
                $injector.get('$controller')("CashBookMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:cashBookMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
