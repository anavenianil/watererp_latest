'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('merchantMaster', {
                parent: 'entity',
                url: '/merchantMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MerchantMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/merchantMaster/merchantMasters.html',
                        controller: 'MerchantMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('merchantMaster.detail', {
                parent: 'entity',
                url: '/merchantMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MerchantMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/merchantMaster/merchantMaster-detail.html',
                        controller: 'MerchantMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MerchantMaster', function($stateParams, MerchantMaster) {
                        return MerchantMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('merchantMaster.new', {
                parent: 'merchantMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/merchantMaster/merchantMaster-dialog.html',
                        controller: 'MerchantMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    merchantCode: null,
                                    merchantName: null,
                                    merchantKey: null,
                                    currency: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('merchantMaster', null, { reload: true });
                    }, function() {
                        $state.go('merchantMaster');
                    })
                }]
            })
            .state('merchantMaster.edit', {
                parent: 'merchantMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/merchantMaster/merchantMaster-dialog.html',
                        controller: 'MerchantMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MerchantMaster', function(MerchantMaster) {
                                return MerchantMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('merchantMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('merchantMaster.delete', {
                parent: 'merchantMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/merchantMaster/merchantMaster-delete-dialog.html',
                        controller: 'MerchantMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MerchantMaster', function(MerchantMaster) {
                                return MerchantMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('merchantMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
