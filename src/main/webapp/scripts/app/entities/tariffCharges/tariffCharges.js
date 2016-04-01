'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tariffCharges', {
                parent: 'entity',
                url: '/tariffChargess',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffChargess'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffCharges/tariffChargess.html',
                        controller: 'TariffChargesController'
                    }
                },
                resolve: {
                }
            })
            .state('tariffCharges.detail', {
                parent: 'entity',
                url: '/tariffCharges/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffCharges'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffCharges/tariffCharges-detail.html',
                        controller: 'TariffChargesDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TariffCharges', function($stateParams, TariffCharges) {
                        return TariffCharges.get({id : $stateParams.id});
                    }]
                }
            })
            .state('tariffCharges.new', {
                parent: 'tariffCharges',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCharges/tariffCharges-dialog.html',
                        controller: 'TariffChargesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    tariffDesc: null,
                                    slabMin: null,
                                    slabMax: null,
                                    rate: null,
                                    minKL: null,
                                    minUnmeteredKL: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCharges', null, { reload: true });
                    }, function() {
                        $state.go('tariffCharges');
                    })
                }]
            })
            .state('tariffCharges.edit', {
                parent: 'tariffCharges',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCharges/tariffCharges-dialog.html',
                        controller: 'TariffChargesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TariffCharges', function(TariffCharges) {
                                return TariffCharges.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCharges', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('tariffCharges.delete', {
                parent: 'tariffCharges',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCharges/tariffCharges-delete-dialog.html',
                        controller: 'TariffChargesDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TariffCharges', function(TariffCharges) {
                                return TariffCharges.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCharges', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
