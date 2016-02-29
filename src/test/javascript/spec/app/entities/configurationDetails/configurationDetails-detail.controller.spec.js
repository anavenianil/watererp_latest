'use strict';

describe('Controller Tests', function() {

    describe('ConfigurationDetails Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockConfigurationDetails;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockConfigurationDetails = jasmine.createSpy('MockConfigurationDetails');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ConfigurationDetails': MockConfigurationDetails
            };
            createController = function() {
                $injector.get('$controller')("ConfigurationDetailsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:configurationDetailsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
