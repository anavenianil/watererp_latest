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
                                    categoryUnused: null,
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
                                    mobileNo: null,
                                    ccFlag: null,
                                    cpFlag: null,
                                    noticeFlag: null,
                                    drFlag: null,
                                    lat: null,
                                    longi: null,
                                    meterFixDate: null,
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
            })*/
            /*.state('custDetails.edit', {
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
            .state('custDetails.categoryChange', {
                parent: 'custDetails',
                url: '/categoryChange',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/custDetails/custDetailsCategoryChange.html',
                        controller: 'CustDetailsCategoryChangeController'
                    }
                },
                resolve: {
                }
            })
            .state('custDetails.pipeSizeChange', {
                parent: 'custDetails',
                url: '/pipeSizeChange',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/custDetails/custDetailsPipeSizeChange.html',
                        controller: 'CustDetailsPipeSizeChangeController'
                    }
                },
                resolve: {
                }
            });
    });
