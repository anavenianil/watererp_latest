'use strict';

describe('Controller Tests', function() {

    describe('Proceedings Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProceedings, MockApplicationTxn;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProceedings = jasmine.createSpy('MockProceedings');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Proceedings': MockProceedings,
                'ApplicationTxn': MockApplicationTxn
            };
            createController = function() {
                $injector.get('$controller')("ProceedingsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:proceedingsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
