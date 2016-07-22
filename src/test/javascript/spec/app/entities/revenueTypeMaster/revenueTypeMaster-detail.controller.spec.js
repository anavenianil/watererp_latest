'use strict';

describe('Controller Tests', function() {

    describe('RevenueTypeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRevenueTypeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRevenueTypeMaster = jasmine.createSpy('MockRevenueTypeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RevenueTypeMaster': MockRevenueTypeMaster
            };
            createController = function() {
                $injector.get('$controller')("RevenueTypeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:revenueTypeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
