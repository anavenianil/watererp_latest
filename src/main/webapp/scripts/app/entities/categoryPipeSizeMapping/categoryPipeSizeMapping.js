'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('categoryPipeSizeMapping', {
                parent: 'entity',
                url: '/categoryPipeSizeMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CategoryPipeSizeMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoryPipeSizeMapping/categoryPipeSizeMappings.html',
                        controller: 'CategoryPipeSizeMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('categoryPipeSizeMapping.detail', {
                parent: 'entity',
                url: '/categoryPipeSizeMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CategoryPipeSizeMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoryPipeSizeMapping/categoryPipeSizeMapping-detail.html',
                        controller: 'CategoryPipeSizeMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CategoryPipeSizeMapping', function($stateParams, CategoryPipeSizeMapping) {
                        return CategoryPipeSizeMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('categoryPipeSizeMapping.new', {
                parent: 'categoryPipeSizeMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryPipeSizeMapping/categoryPipeSizeMapping-dialog.html',
                        controller: 'CategoryPipeSizeMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('categoryPipeSizeMapping', null, { reload: true });
                    }, function() {
                        $state.go('categoryPipeSizeMapping');
                    })
                }]
            })
            .state('categoryPipeSizeMapping.edit', {
                parent: 'categoryPipeSizeMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryPipeSizeMapping/categoryPipeSizeMapping-dialog.html',
                        controller: 'CategoryPipeSizeMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CategoryPipeSizeMapping', function(CategoryPipeSizeMapping) {
                                return CategoryPipeSizeMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('categoryPipeSizeMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('categoryPipeSizeMapping.delete', {
                parent: 'categoryPipeSizeMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryPipeSizeMapping/categoryPipeSizeMapping-delete-dialog.html',
                        controller: 'CategoryPipeSizeMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CategoryPipeSizeMapping', function(CategoryPipeSizeMapping) {
                                return CategoryPipeSizeMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('categoryPipeSizeMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
