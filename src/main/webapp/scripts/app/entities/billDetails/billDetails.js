'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billDetails', {
                parent: 'entity',
                url: '/billDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetailss.html',
                        controller: 'BillDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.detail', {
                parent: 'entity',
                url: '/billDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-detail.html',
                        controller: 'BillDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillDetails', function($stateParams, BillDetails) {
                        return BillDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billDetails.new', {
                parent: 'billDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.edit', {
                parent: 'billDetails',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('billDetails.new', {
                parent: 'billDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
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
                                    metReaderCode: null,
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
                                    metReadingDt: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('billDetails');
                    })
                }]
            })
            .state('billDetails.edit', {
                parent: 'billDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillDetails', function(BillDetails) {
                                return BillDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('billDetails.delete', {
                parent: 'billDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-delete-dialog.html',
                        controller: 'BillDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillDetails', function(BillDetails) {
                                return BillDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
