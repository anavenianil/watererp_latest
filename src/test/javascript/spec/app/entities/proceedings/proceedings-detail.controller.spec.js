'use strict';

describe('Controller Tests', function() {

    describe('Proceedings Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProceedings, MockPercentageMaster, MockApplicationTxn, MockItemRequired;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProceedings = jasmine.createSpy('MockProceedings');
            MockPercentageMaster = jasmine.createSpy('MockPercentageMaster');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockItemRequired = jasmine.createSpy('MockItemRequired');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Proceedings': MockProceedings,
                'PercentageMaster': MockPercentageMaster,
                'ApplicationTxn': MockApplicationTxn,
                'ItemRequired': MockItemRequired
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
