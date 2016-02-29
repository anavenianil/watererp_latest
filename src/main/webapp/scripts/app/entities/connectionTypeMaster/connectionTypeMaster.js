'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('connectionTypeMaster', {
                parent: 'entity',
                url: '/connectionTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ConnectionTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/connectionTypeMaster/connectionTypeMasters.html',
                        controller: 'ConnectionTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('connectionTypeMaster.detail', {
                parent: 'entity',
                url: '/connectionTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ConnectionTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/connectionTypeMaster/connectionTypeMaster-detail.html',
                        controller: 'ConnectionTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ConnectionTypeMaster', function($stateParams, ConnectionTypeMaster) {
                        return ConnectionTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('connectionTypeMaster.new', {
                parent: 'connectionTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTypeMaster/connectionTypeMaster-dialog.html',
                        controller: 'ConnectionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    connectionType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('connectionTypeMaster');
                    })
                }]
            })
            .state('connectionTypeMaster.edit', {
                parent: 'connectionTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTypeMaster/connectionTypeMaster-dialog.html',
                        controller: 'ConnectionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ConnectionTypeMaster', function(ConnectionTypeMaster) {
                                return ConnectionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('connectionTypeMaster.delete', {
                parent: 'connectionTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTypeMaster/connectionTypeMaster-delete-dialog.html',
                        controller: 'ConnectionTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ConnectionTypeMaster', function(ConnectionTypeMaster) {
                                return ConnectionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
