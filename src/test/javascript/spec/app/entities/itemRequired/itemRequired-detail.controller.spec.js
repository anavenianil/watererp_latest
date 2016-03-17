'use strict';

describe('Controller Tests', function() {

    describe('ItemRequired Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemRequired, MockItemDetails, MockFeasibilityStudy, MockApplicationTxn;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemRequired = jasmine.createSpy('MockItemRequired');
            MockItemDetails = jasmine.createSpy('MockItemDetails');
            MockFeasibilityStudy = jasmine.createSpy('MockFeasibilityStudy');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemRequired': MockItemRequired,
                'ItemDetails': MockItemDetails,
                'FeasibilityStudy': MockFeasibilityStudy,
                'ApplicationTxn': MockApplicationTxn
            };
            createController = function() {
                $injector.get('$controller')("ItemRequiredDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemRequiredUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
