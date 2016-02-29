'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cashBookMaster', {
                parent: 'entity',
                url: '/cashBookMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CashBookMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cashBookMaster/cashBookMasters.html',
                        controller: 'CashBookMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('cashBookMaster.detail', {
                parent: 'entity',
                url: '/cashBookMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CashBookMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cashBookMaster/cashBookMaster-detail.html',
                        controller: 'CashBookMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CashBookMaster', function($stateParams, CashBookMaster) {
                        return CashBookMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('cashBookMaster.new', {
                parent: 'cashBookMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/cashBookMaster/cashBookMaster-dialog.html',
                        controller: 'CashBookMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    cashBookEntryType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('cashBookMaster', null, { reload: true });
                    }, function() {
                        $state.go('cashBookMaster');
                    })
                }]
            })
            .state('cashBookMaster.edit', {
                parent: 'cashBookMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/cashBookMaster/cashBookMaster-dialog.html',
                        controller: 'CashBookMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CashBookMaster', function(CashBookMaster) {
                                return CashBookMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('cashBookMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('cashBookMaster.delete', {
                parent: 'cashBookMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/cashBookMaster/cashBookMaster-delete-dialog.html',
                        controller: 'CashBookMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CashBookMaster', function(CashBookMaster) {
                                return CashBookMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('cashBookMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
