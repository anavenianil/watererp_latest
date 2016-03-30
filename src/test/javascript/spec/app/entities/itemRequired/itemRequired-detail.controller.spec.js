'use strict';

describe('Controller Tests', function() {

    describe('ItemRequired Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemRequired, MockMaterialMaster, MockApplicationTxn, MockFeasibilityStudy, MockProceedings;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemRequired = jasmine.createSpy('MockItemRequired');
            MockMaterialMaster = jasmine.createSpy('MockMaterialMaster');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockFeasibilityStudy = jasmine.createSpy('MockFeasibilityStudy');
            MockProceedings = jasmine.createSpy('MockProceedings');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemRequired': MockItemRequired,
                'MaterialMaster': MockMaterialMaster,
                'ApplicationTxn': MockApplicationTxn,
                'FeasibilityStudy': MockFeasibilityStudy,
                'Proceedings': MockProceedings
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
