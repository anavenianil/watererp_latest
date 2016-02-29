'use strict';

describe('Controller Tests', function() {

    describe('TransactionTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTransactionTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTransactionTypeMaster = jasmine.createSpy('MockTransactionTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TransactionTypeMaster': MockTransactionTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("TransactionTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:transactionTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
