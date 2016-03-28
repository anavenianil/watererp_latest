'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('custDetails', {
                parent: 'entity',
                url: '/custDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/custDetails/custDetailss.html',
                        controller: 'CustDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('custDetails.detail', {
                parent: 'entity',
                url: '/custDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/custDetails/custDetails-detail.html',
                        controller: 'CustDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CustDetails', function($stateParams, CustDetails) {
                        return CustDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('custDetails.new', {
                parent: 'custDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/custDetails/custDetails-dialog.html',
                        controller: 'CustDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('custDetails.edit', {
                parent: 'custDetails',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/custDetails/custDetails-dialog.html',
                        controller: 'CustDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('custDetails.new', {
                parent: 'custDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custDetails/custDetails-dialog.html',
                        controller: 'CustDetailsDialogController',
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
                                    surCharge: null,
                                    hrsSurCharge: null,
                                    resUnits: null,
                                    metCostInstallment: null,
                                    intOnArrears: null,
                                    lastPymtDt: null,
                                    lastPymtAmt: null,
                                    mobileNo: null,
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
                                    mcMetReaderCode: null,
                                    billFlag: null,
                                    docket: null,
                                    ocFlag: null,
                                    ocDate: null,
                                    lat: null,
                                    longI: null,
                                    noMeterFlag: null,
                                    noMeterAckDt: null,
                                    noMeterAmt: null,
                                    meterTampAmt: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('custDetails', null, { reload: true });
                    }, function() {
                        $state.go('custDetails');
                    })
                }]
            })
            .state('custDetails.edit', {
                parent: 'custDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custDetails/custDetails-dialog.html',
                        controller: 'CustDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CustDetails', function(CustDetails) {
                                return CustDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('custDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('custDetails.delete', {
                parent: 'custDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custDetails/custDetails-delete-dialog.html',
                        controller: 'CustDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CustDetails', function(CustDetails) {
                                return CustDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('custDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
