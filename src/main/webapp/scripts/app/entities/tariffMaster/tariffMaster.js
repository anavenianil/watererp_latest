'use strict';

angular.module('waterERPApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tariffMaster', {
                parent: 'entity',
                url: '/tariffMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffMaster/tariffMasters.html',
                        controller: 'TariffMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('tariffMaster.detail', {
                parent: 'entity',
                url: '/tariffMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffMaster/tariffMaster-detail.html',
                        controller: 'TariffMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TariffMaster', function($stateParams, TariffMaster) {
                        return TariffMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('tariffMaster.new', {
                parent: 'tariffMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffMaster/tariffMaster-dialog.html',
                        controller: 'TariffMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    tariffName: null,
                                    validFrom: null,
                                    validTo: null,
                                    active: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('tariffMaster', null, { reload: true });
                    }, function() {
                        $state.go('tariffMaster');
                    })
                }]
            })
            .state('tariffMaster.edit', {
                parent: 'tariffMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffMaster/tariffMaster-dialog.html',
                        controller: 'TariffMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TariffMaster', function(TariffMaster) {
                                return TariffMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('tariffMaster.delete', {
                parent: 'tariffMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffMaster/tariffMaster-delete-dialog.html',
                        controller: 'TariffMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TariffMaster', function(TariffMaster) {
                                return TariffMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
