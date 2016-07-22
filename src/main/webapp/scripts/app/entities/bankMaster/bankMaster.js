'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('bankMaster', {
                parent: 'entity',
                url: '/bankMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BankMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/bankMaster/bankMasters.html',
                        controller: 'BankMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('bankMaster.detail', {
                parent: 'entity',
                url: '/bankMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BankMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/bankMaster/bankMaster-detail.html',
                        controller: 'BankMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BankMaster', function($stateParams, BankMaster) {
                        return BankMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('bankMaster.new', {
                parent: 'bankMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/bankMaster/bankMaster-dialog.html',
                        controller: 'BankMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    bankCode: null,
                                    bankName: null,
                                    bankBranch: null,
                                    bankAccount: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('bankMaster', null, { reload: true });
                    }, function() {
                        $state.go('bankMaster');
                    })
                }]
            })
            .state('bankMaster.edit', {
                parent: 'bankMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/bankMaster/bankMaster-dialog.html',
                        controller: 'BankMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BankMaster', function(BankMaster) {
                                return BankMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('bankMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('bankMaster.delete', {
                parent: 'bankMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/bankMaster/bankMaster-delete-dialog.html',
                        controller: 'BankMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BankMaster', function(BankMaster) {
                                return BankMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('bankMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
