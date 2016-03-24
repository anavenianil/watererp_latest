'use strict';

describe('Controller Tests', function() {

    describe('FeasibilityStudy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFeasibilityStudy, MockDivisionMaster, MockZoneMaster, MockStreetMaster, MockApplicationTxn, MockUser, MockCategoryMaster;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFeasibilityStudy = jasmine.createSpy('MockFeasibilityStudy');
            MockDivisionMaster = jasmine.createSpy('MockDivisionMaster');
            MockZoneMaster = jasmine.createSpy('MockZoneMaster');
            MockStreetMaster = jasmine.createSpy('MockStreetMaster');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockUser = jasmine.createSpy('MockUser');
            MockCategoryMaster = jasmine.createSpy('MockCategoryMaster');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'FeasibilityStudy': MockFeasibilityStudy,
                'DivisionMaster': MockDivisionMaster,
                'ZoneMaster': MockZoneMaster,
                'StreetMaster': MockStreetMaster,
                'ApplicationTxn': MockApplicationTxn,
                'User': MockUser,
                'CategoryMaster': MockCategoryMaster
            };
            createController = function() {
                $injector.get('$controller')("FeasibilityStudyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'watererpApp:feasibilityStudyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
