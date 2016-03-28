'use strict';

describe('Controller Tests', function() {

    describe('FileUploadMaster Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFileUploadMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFileUploadMaster = jasmine.createSpy('MockFileUploadMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'FileUploadMaster': MockFileUploadMaster
            };
            createController = function() {
                $injector.get('$controller')("FileUploadMasterDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:fileUploadMasterUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
