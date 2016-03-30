'use strict';

describe('Controller Tests', function() {

    describe('ItemCompanyMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemCompanyMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemCompanyMaster = jasmine.createSpy('MockItemCompanyMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemCompanyMaster': MockItemCompanyMaster
            };
            createController = function() {
                $injector.get('$controller')("ItemCompanyMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemCompanyMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
