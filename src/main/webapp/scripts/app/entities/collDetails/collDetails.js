'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('collDetails', {
                parent: 'entity',
                url: '/collDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/collDetailss.html',
                        controller: 'CollDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('collDetails.detail', {
                parent: 'entity',
                url: '/collDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CollDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-detail.html',
                        controller: 'CollDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CollDetails', function($stateParams, CollDetails) {
                        return CollDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('collDetails.new', {
                parent: 'collDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-dialog.html',
                        controller: 'CollDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    reversalRef: null,
                                    receiptNo: null,
                                    receiptAmt: null,
                                    receiptDt: null,
                                    receiptMode: null,
                                    instrNo: null,
                                    instrDt: null,
                                    instrIssuer: null,
                                    svrStatus: null,
                                    can: null,
                                    consName: null,
                                    terminalId: null,
                                    collTime: null,
                                    txnStatus: null,
                                    meterReaderId: null,
                                    userId: null,
                                    remarks: null,
                                    settlementId: null,
                                    extSettlementId: null,
                                    lat: null,
                                    longI: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('collDetails', null, { reload: true });
                    }, function() {
                        $state.go('collDetails');
                    })
                }]
            })
            .state('collDetails.edit', {
                parent: 'collDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-dialog.html',
                        controller: 'CollDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CollDetails', function(CollDetails) {
                                return CollDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('collDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('collDetails.delete', {
                parent: 'collDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-delete-dialog.html',
                        controller: 'CollDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CollDetails', function(CollDetails) {
                                return CollDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('collDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
