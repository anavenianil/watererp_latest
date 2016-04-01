'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tariffTypeMaster', {
                parent: 'entity',
                url: '/tariffTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffTypeMaster/tariffTypeMasters.html',
                        controller: 'TariffTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('tariffTypeMaster.detail', {
                parent: 'entity',
                url: '/tariffTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffTypeMaster/tariffTypeMaster-detail.html',
                        controller: 'TariffTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TariffTypeMaster', function($stateParams, TariffTypeMaster) {
                        return TariffTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('tariffTypeMaster.new', {
                parent: 'tariffTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffTypeMaster/tariffTypeMaster-dialog.html',
                        controller: 'TariffTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    tariffType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('tariffTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('tariffTypeMaster');
                    })
                }]
            })
            .state('tariffTypeMaster.edit', {
                parent: 'tariffTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffTypeMaster/tariffTypeMaster-dialog.html',
                        controller: 'TariffTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TariffTypeMaster', function(TariffTypeMaster) {
                                return TariffTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('tariffTypeMaster.delete', {
                parent: 'tariffTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffTypeMaster/tariffTypeMaster-delete-dialog.html',
                        controller: 'TariffTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TariffTypeMaster', function(TariffTypeMaster) {
                                return TariffTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
