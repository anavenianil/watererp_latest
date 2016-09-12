'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billFullDetails', {
                parent: 'entity',
                url: '/billFullDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillFullDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/billFullDetailss.html',
                        controller: 'BillFullDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billFullDetails.detail', {
                parent: 'entity',
                url: '/billFullDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillFullDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/billFullDetails-detail.html',
                        controller: 'BillFullDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillFullDetails', function($stateParams, BillFullDetails) {
                        return BillFullDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billFullDetails.new', {
                parent: 'billFullDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billFullDetails/billFullDetails-dialog.html',
                        controller: 'BillFullDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    divCode: null,
                                    secCode: null,
                                    secName: null,
                                    metReaderCode: null,
                                    connDate: null,
                                    consName: null,
                                    houseNo: null,
                                    address: null,
                                    city: null,
                                    pinCode: null,
                                    category: null,
                                    pipeSize: null,
                                    boardMeter: null,
                                    sewerage: null,
                                    meterNo: null,
                                    prevBillType: null,
                                    prevBillMonth: null,
                                    prevAvgKl: null,
                                    metReadingDt: null,
                                    prevReading: null,
                                    metReadingMo: null,
                                    metAvgKl: null,
                                    arrears: null,
                                    reversalAmt: null,
                                    installment: null,
                                    otherCharges: null,
                                    surcharge: null,
                                    hrsSurcharge: null,
                                    resUnits: null,
                                    metCostInstallment: null,
                                    intOnArrears: null,
                                    lastPymtDt: null,
                                    lastPymtAmt: null,
                                    billNumber: null,
                                    billDate: null,
                                    billTime: null,
                                    meterMake: null,
                                    currentBillType: null,
                                    fromMonth: null,
                                    toMonth: null,
                                    meterFixDate: null,
                                    initialReading: null,
                                    presentReading: null,
                                    units: null,
                                    waterCess: null,
                                    sewerageCess: null,
                                    serviceCharge: null,
                                    meterServiceCharge: null,
                                    totalAmount: null,
                                    netPayableAmount: null,
                                    telephoneNo: null,
                                    meterStatus: null,
                                    billFlag: null,
                                    svrStatus: null,
                                    terminalId: null,
                                    meterReaderId: null,
                                    userId: null,
                                    mobileNo: null,
                                    noticeNo: null,
                                    lat: null,
                                    longi: null,
                                    noMeterAmt: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billFullDetails', null, { reload: true });
                    }, function() {
                        $state.go('billFullDetails');
                    })
                }]
            })
            .state('billFullDetails.edit', {
                parent: 'billFullDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billFullDetails/billFullDetails-dialog.html',
                        controller: 'BillFullDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillFullDetails', function(BillFullDetails) {
                                return BillFullDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billFullDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('billFullDetails.delete', {
                parent: 'billFullDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billFullDetails/billFullDetails-delete-dialog.html',
                        controller: 'BillFullDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillFullDetails', function(BillFullDetails) {
                                return BillFullDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billFullDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('getWaterBillDetails', {
                parent: 'billFullDetails',
                url: '/getWaterBillDetails',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterBillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/getWaterBillDetailss.html',
                        controller: 'GetWaterBillDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billReport', {
                parent: 'billFullDetails',
                url: '/billReport',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterBillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/billReport.html',
                        controller: 'BillReportController'
                    }
                },
                resolve: {
                }
            })
            .state('ageAnalysisReport', {
                parent: 'billFullDetails',
                url: '/ageAnalysisReport',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterBillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/ageAnalysisReport.html',
                        controller: 'AgeAnalysisReportController'
                    }
                },
                resolve: {
                }
            })
            .state('waterBillReport', {
                parent: 'billFullDetails',
                url: '/waterBillReport',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterBillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billFullDetails/waterBillReport.html',
                        controller: 'WaterBillReportController'
                    }
                },
                resolve: {
                }
            });
    });
