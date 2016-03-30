'use strict';

describe('Controller Tests', function() {

    describe('ItemSubCodeMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockItemSubCodeMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockItemSubCodeMaster = jasmine.createSpy('MockItemSubCodeMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ItemSubCodeMaster': MockItemSubCodeMaster
            };
            createController = function() {
                $injector.get('$controller')("ItemSubCodeMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:itemSubCodeMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
