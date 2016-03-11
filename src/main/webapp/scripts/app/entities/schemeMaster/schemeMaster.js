'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('schemeMaster', {
                parent: 'entity',
                url: '/schemeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SchemeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/schemeMaster/schemeMasters.html',
                        controller: 'SchemeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('schemeMaster.detail', {
                parent: 'entity',
                url: '/schemeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SchemeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/schemeMaster/schemeMaster-detail.html',
                        controller: 'SchemeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SchemeMaster', function($stateParams, SchemeMaster) {
                        return SchemeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('schemeMaster.new', {
                parent: 'schemeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/schemeMaster/schemeMaster-dialog.html',
                        controller: 'SchemeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    schemeName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('schemeMaster', null, { reload: true });
                    }, function() {
                        $state.go('schemeMaster');
                    })
                }]
            })
            .state('schemeMaster.edit', {
                parent: 'schemeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/schemeMaster/schemeMaster-dialog.html',
                        controller: 'SchemeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['SchemeMaster', function(SchemeMaster) {
                                return SchemeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('schemeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('schemeMaster.delete', {
                parent: 'schemeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/schemeMaster/schemeMaster-delete-dialog.html',
                        controller: 'SchemeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SchemeMaster', function(SchemeMaster) {
                                return SchemeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('schemeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
