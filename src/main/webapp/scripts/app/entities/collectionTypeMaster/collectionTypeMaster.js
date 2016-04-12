'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('collectionTypeMaster', {
                parent: 'entity',
                url: '/collectionTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CollectionTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collectionTypeMaster/collectionTypeMasters.html',
                        controller: 'CollectionTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('collectionTypeMaster.detail', {
                parent: 'entity',
                url: '/collectionTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CollectionTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collectionTypeMaster/collectionTypeMaster-detail.html',
                        controller: 'CollectionTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CollectionTypeMaster', function($stateParams, CollectionTypeMaster) {
                        return CollectionTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('collectionTypeMaster.new', {
                parent: 'collectionTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collectionTypeMaster/collectionTypeMaster-dialog.html',
                        controller: 'CollectionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    collName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('collectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('collectionTypeMaster');
                    })
                }]
            })
            .state('collectionTypeMaster.edit', {
                parent: 'collectionTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collectionTypeMaster/collectionTypeMaster-dialog.html',
                        controller: 'CollectionTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CollectionTypeMaster', function(CollectionTypeMaster) {
                                return CollectionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('collectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('collectionTypeMaster.delete', {
                parent: 'collectionTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collectionTypeMaster/collectionTypeMaster-delete-dialog.html',
                        controller: 'CollectionTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CollectionTypeMaster', function(CollectionTypeMaster) {
                                return CollectionTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('collectionTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
