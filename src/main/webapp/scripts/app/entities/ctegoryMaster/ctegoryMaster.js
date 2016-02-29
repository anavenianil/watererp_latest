'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ctegoryMaster', {
                parent: 'entity',
                url: '/ctegoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CtegoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ctegoryMaster/ctegoryMasters.html',
                        controller: 'CtegoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('ctegoryMaster.detail', {
                parent: 'entity',
                url: '/ctegoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CtegoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ctegoryMaster/ctegoryMaster-detail.html',
                        controller: 'CtegoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CtegoryMaster', function($stateParams, CtegoryMaster) {
                        return CtegoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('ctegoryMaster.new', {
                parent: 'ctegoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ctegoryMaster/ctegoryMaster-dialog.html',
                        controller: 'CtegoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    categoryName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('ctegoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('ctegoryMaster');
                    })
                }]
            })
            .state('ctegoryMaster.edit', {
                parent: 'ctegoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ctegoryMaster/ctegoryMaster-dialog.html',
                        controller: 'CtegoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CtegoryMaster', function(CtegoryMaster) {
                                return CtegoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ctegoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('ctegoryMaster.delete', {
                parent: 'ctegoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/ctegoryMaster/ctegoryMaster-delete-dialog.html',
                        controller: 'CtegoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CtegoryMaster', function(CtegoryMaster) {
                                return CtegoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('ctegoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
