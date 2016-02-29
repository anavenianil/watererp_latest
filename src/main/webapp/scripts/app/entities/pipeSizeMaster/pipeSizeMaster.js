'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('pipeSizeMaster', {
                parent: 'entity',
                url: '/pipeSizeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PipeSizeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pipeSizeMaster/pipeSizeMasters.html',
                        controller: 'PipeSizeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('pipeSizeMaster.detail', {
                parent: 'entity',
                url: '/pipeSizeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PipeSizeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pipeSizeMaster/pipeSizeMaster-detail.html',
                        controller: 'PipeSizeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PipeSizeMaster', function($stateParams, PipeSizeMaster) {
                        return PipeSizeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('pipeSizeMaster.new', {
                parent: 'pipeSizeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/pipeSizeMaster/pipeSizeMaster-dialog.html',
                        controller: 'PipeSizeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    pipeSize: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('pipeSizeMaster', null, { reload: true });
                    }, function() {
                        $state.go('pipeSizeMaster');
                    })
                }]
            })
            .state('pipeSizeMaster.edit', {
                parent: 'pipeSizeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/pipeSizeMaster/pipeSizeMaster-dialog.html',
                        controller: 'PipeSizeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PipeSizeMaster', function(PipeSizeMaster) {
                                return PipeSizeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('pipeSizeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('pipeSizeMaster.delete', {
                parent: 'pipeSizeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/pipeSizeMaster/pipeSizeMaster-delete-dialog.html',
                        controller: 'PipeSizeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['PipeSizeMaster', function(PipeSizeMaster) {
                                return PipeSizeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('pipeSizeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
