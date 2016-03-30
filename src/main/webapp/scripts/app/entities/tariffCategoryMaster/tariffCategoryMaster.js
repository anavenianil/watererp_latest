'use strict';

angular.module('waterERPApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tariffCategoryMaster', {
                parent: 'entity',
                url: '/tariffCategoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffCategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffCategoryMaster/tariffCategoryMasters.html',
                        controller: 'TariffCategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('tariffCategoryMaster.detail', {
                parent: 'entity',
                url: '/tariffCategoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TariffCategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tariffCategoryMaster/tariffCategoryMaster-detail.html',
                        controller: 'TariffCategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TariffCategoryMaster', function($stateParams, TariffCategoryMaster) {
                        return TariffCategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('tariffCategoryMaster.new', {
                parent: 'tariffCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCategoryMaster/tariffCategoryMaster-dialog.html',
                        controller: 'TariffCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    tariffCategory: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('tariffCategoryMaster');
                    })
                }]
            })
            .state('tariffCategoryMaster.edit', {
                parent: 'tariffCategoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCategoryMaster/tariffCategoryMaster-dialog.html',
                        controller: 'TariffCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TariffCategoryMaster', function(TariffCategoryMaster) {
                                return TariffCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('tariffCategoryMaster.delete', {
                parent: 'tariffCategoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/tariffCategoryMaster/tariffCategoryMaster-delete-dialog.html',
                        controller: 'TariffCategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TariffCategoryMaster', function(TariffCategoryMaster) {
                                return TariffCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('tariffCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
