'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billRunMaster', {
                parent: 'entity',
                url: '/billRunMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillRunMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billRunMaster/billRunMasters.html',
                        controller: 'BillRunMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('billRunMaster.detail', {
                parent: 'entity',
                url: '/billRunMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillRunMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billRunMaster/billRunMaster-detail.html',
                        controller: 'BillRunMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillRunMaster', function($stateParams, BillRunMaster) {
                        return BillRunMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billRunMaster.new', {
                parent: 'billRunMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunMaster/billRunMaster-dialog.html',
                        controller: 'BillRunMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    date: null,
                                    area: null,
                                    success: null,
                                    failed: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billRunMaster', null, { reload: true });
                    }, function() {
                        $state.go('billRunMaster');
                    })
                }]
            })
            .state('billRunMaster.edit', {
                parent: 'billRunMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunMaster/billRunMaster-dialog.html',
                        controller: 'BillRunMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillRunMaster', function(BillRunMaster) {
                                return BillRunMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billRunMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('billRunMaster.delete', {
                parent: 'billRunMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunMaster/billRunMaster-delete-dialog.html',
                        controller: 'BillRunMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillRunMaster', function(BillRunMaster) {
                                return BillRunMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billRunMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
