'use strict';

describe('Controller Tests', function() {

    describe('FeasibilityStudy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockFeasibilityStudy, MockSchemeMaster, MockTariffCategoryMaster, MockMakeOfPipe, MockMainWaterSize, MockMainSewerageSize, MockDocketCode, MockApplicationTxn, MockCategoryMaster, MockSewerSize, MockPipeSizeMaster, MockFeasibilityStatus;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockFeasibilityStudy = jasmine.createSpy('MockFeasibilityStudy');
            MockSchemeMaster = jasmine.createSpy('MockSchemeMaster');
            MockTariffCategoryMaster = jasmine.createSpy('MockTariffCategoryMaster');
            MockMakeOfPipe = jasmine.createSpy('MockMakeOfPipe');
            MockMainWaterSize = jasmine.createSpy('MockMainWaterSize');
            MockMainSewerageSize = jasmine.createSpy('MockMainSewerageSize');
            MockDocketCode = jasmine.createSpy('MockDocketCode');
            MockApplicationTxn = jasmine.createSpy('MockApplicationTxn');
            MockCategoryMaster = jasmine.createSpy('MockCategoryMaster');
            MockSewerSize = jasmine.createSpy('MockSewerSize');
            MockPipeSizeMaster = jasmine.createSpy('MockPipeSizeMaster');
            MockFeasibilityStatus = jasmine.createSpy('MockFeasibilityStatus');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'FeasibilityStudy': MockFeasibilityStudy,
                'SchemeMaster': MockSchemeMaster,
                'TariffCategoryMaster': MockTariffCategoryMaster,
                'MakeOfPipe': MockMakeOfPipe,
                'MainWaterSize': MockMainWaterSize,
                'MainSewerageSize': MockMainSewerageSize,
                'DocketCode': MockDocketCode,
                'ApplicationTxn': MockApplicationTxn,
                'CategoryMaster': MockCategoryMaster,
                'SewerSize': MockSewerSize,
                'PipeSizeMaster': MockPipeSizeMaster,
                'FeasibilityStatus': MockFeasibilityStatus
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
