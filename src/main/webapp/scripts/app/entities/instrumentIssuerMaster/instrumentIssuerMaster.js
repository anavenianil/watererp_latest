'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('instrumentIssuerMaster', {
                parent: 'entity',
                url: '/instrumentIssuerMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'InstrumentIssuerMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/instrumentIssuerMaster/instrumentIssuerMasters.html',
                        controller: 'InstrumentIssuerMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('instrumentIssuerMaster.detail', {
                parent: 'entity',
                url: '/instrumentIssuerMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'InstrumentIssuerMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/instrumentIssuerMaster/instrumentIssuerMaster-detail.html',
                        controller: 'InstrumentIssuerMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'InstrumentIssuerMaster', function($stateParams, InstrumentIssuerMaster) {
                        return InstrumentIssuerMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('instrumentIssuerMaster.new', {
                parent: 'instrumentIssuerMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/instrumentIssuerMaster/instrumentIssuerMaster-dialog.html',
                        controller: 'InstrumentIssuerMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    instrumentIssuer: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('instrumentIssuerMaster', null, { reload: true });
                    }, function() {
                        $state.go('instrumentIssuerMaster');
                    })
                }]
            })
            .state('instrumentIssuerMaster.edit', {
                parent: 'instrumentIssuerMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/instrumentIssuerMaster/instrumentIssuerMaster-dialog.html',
                        controller: 'InstrumentIssuerMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['InstrumentIssuerMaster', function(InstrumentIssuerMaster) {
                                return InstrumentIssuerMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('instrumentIssuerMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('instrumentIssuerMaster.delete', {
                parent: 'instrumentIssuerMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/instrumentIssuerMaster/instrumentIssuerMaster-delete-dialog.html',
                        controller: 'InstrumentIssuerMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['InstrumentIssuerMaster', function(InstrumentIssuerMaster) {
                                return InstrumentIssuerMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('instrumentIssuerMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
