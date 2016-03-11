'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('categoryMaster', {
                parent: 'entity',
                url: '/categoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoryMaster/categoryMasters.html',
                        controller: 'CategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('categoryMaster.detail', {
                parent: 'entity',
                url: '/categoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/categoryMaster/categoryMaster-detail.html',
                        controller: 'CategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CategoryMaster', function($stateParams, CategoryMaster) {
                        return CategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('categoryMaster.new', {
                parent: 'categoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryMaster/categoryMaster-dialog.html',
                        controller: 'CategoryMasterDialogController',
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
                        $state.go('categoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('categoryMaster');
                    })
                }]
            })
            .state('categoryMaster.edit', {
                parent: 'categoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryMaster/categoryMaster-dialog.html',
                        controller: 'CategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CategoryMaster', function(CategoryMaster) {
                                return CategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('categoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('categoryMaster.delete', {
                parent: 'categoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/categoryMaster/categoryMaster-delete-dialog.html',
                        controller: 'CategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CategoryMaster', function(CategoryMaster) {
                                return CategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('categoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
