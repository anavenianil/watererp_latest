'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('manageCashPoint', {
                parent: 'entity',
                url: '/manageCashPoints',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ManageCashPoints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/manageCashPoint/manageCashPoints.html',
                        controller: 'ManageCashPointController'
                    }
                },
                resolve: {
                }
            })
            .state('manageCashPoint.detail', {
                parent: 'entity',
                url: '/manageCashPoint/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ManageCashPoint'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/manageCashPoint/manageCashPoint-detail.html',
                        controller: 'ManageCashPointDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ManageCashPoint', function($stateParams, ManageCashPoint) {
                        return ManageCashPoint.get({id : $stateParams.id});
                    }]
                }
            })
            .state('manageCashPoint.new', {
                parent: 'manageCashPoint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/manageCashPoint/manageCashPoint-dialog.html',
                        controller: 'ManageCashPointDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    todayDate: null,
                                    payeeName: null,
                                    txnAmount: null,
                                    openBal: null,
                                    availBal: null,
                                    totalReceipts: null,
                                    locationCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('manageCashPoint', null, { reload: true });
                    }, function() {
                        $state.go('manageCashPoint');
                    })
                }]
            })
            .state('manageCashPoint.edit', {
                parent: 'manageCashPoint',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/manageCashPoint/manageCashPoint-dialog.html',
                        controller: 'ManageCashPointDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ManageCashPoint', function(ManageCashPoint) {
                                return ManageCashPoint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('manageCashPoint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('manageCashPoint.delete', {
                parent: 'manageCashPoint',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/manageCashPoint/manageCashPoint-delete-dialog.html',
                        controller: 'ManageCashPointDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ManageCashPoint', function(ManageCashPoint) {
                                return ManageCashPoint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('manageCashPoint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
