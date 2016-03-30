'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemCategoryMaster', {
                parent: 'entity',
                url: '/itemCategoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMasters.html',
                        controller: 'ItemCategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCategoryMaster.detail', {
                parent: 'entity',
                url: '/itemCategoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-detail.html',
                        controller: 'ItemCategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemCategoryMaster', function($stateParams, ItemCategoryMaster) {
                        return ItemCategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('itemCategoryMaster.new', {
                parent: 'itemCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-dialog.html',
                        controller: 'ItemCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    categoryCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('itemCategoryMaster');
                    })
                }]
            })*/
            /*.state('itemCategoryMaster.edit', {
                parent: 'itemCategoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-dialog.html',
                        controller: 'ItemCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemCategoryMaster', function(ItemCategoryMaster) {
                                return ItemCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('itemCategoryMaster.delete', {
                parent: 'itemCategoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-delete-dialog.html',
                        controller: 'ItemCategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemCategoryMaster', function(ItemCategoryMaster) {
                                return ItemCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemCategoryMaster.new', {
                parent: 'itemCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCategoryMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-dialog.html',
                        controller: 'ItemCategoryMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCategoryMaster.edit', {
                parent: 'itemCategoryMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCategoryMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCategoryMaster/itemCategoryMaster-dialog.html',
                        controller: 'ItemCategoryMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
