'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('transactionTypeMaster', {
                parent: 'entity',
                url: '/transactionTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TransactionTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/transactionTypeMaster/transactionTypeMasters.html',
                        controller: 'TransactionTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('transactionTypeMaster.detail', {
                parent: 'entity',
                url: '/transactionTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TransactionTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/transactionTypeMaster/transactionTypeMaster-detail.html',
                        controller: 'TransactionTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TransactionTypeMaster', function($stateParams, TransactionTypeMaster) {
                        return TransactionTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('transactionTypeMaster.new', {
                parent: 'transactionTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/transactionTypeMaster/transactionTypeMaster-dialog.html',
                        controller: 'TransactionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    typeOfTxn: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('transactionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('transactionTypeMaster');
                    })
                }]
            })
            .state('transactionTypeMaster.edit', {
                parent: 'transactionTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/transactionTypeMaster/transactionTypeMaster-dialog.html',
                        controller: 'TransactionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TransactionTypeMaster', function(TransactionTypeMaster) {
                                return TransactionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('transactionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('transactionTypeMaster.delete', {
                parent: 'transactionTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/transactionTypeMaster/transactionTypeMaster-delete-dialog.html',
                        controller: 'TransactionTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TransactionTypeMaster', function(TransactionTypeMaster) {
                                return TransactionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('transactionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
