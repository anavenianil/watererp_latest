'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemSubCategoryMaster', {
                parent: 'entity',
                url: '/itemSubCategoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMasters.html',
                        controller: 'ItemSubCategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('itemSubCategoryMaster.detail', {
                parent: 'entity',
                url: '/itemSubCategoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-detail.html',
                        controller: 'ItemSubCategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemSubCategoryMaster', function($stateParams, ItemSubCategoryMaster) {
                        return ItemSubCategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('itemSubCategoryMaster.new', {
                parent: 'itemSubCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-dialog.html',
                        controller: 'ItemSubCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    itemSubCategoryCode: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    name: null,
                                    categoryCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('itemSubCategoryMaster');
                    })
                }]
            })*/
            /*.state('itemSubCategoryMaster.edit', {
                parent: 'itemSubCategoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-dialog.html',
                        controller: 'ItemSubCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemSubCategoryMaster', function(ItemSubCategoryMaster) {
                                return ItemSubCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('itemSubCategoryMaster.delete', {
                parent: 'itemSubCategoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-delete-dialog.html',
                        controller: 'ItemSubCategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemSubCategoryMaster', function(ItemSubCategoryMaster) {
                                return ItemSubCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemSubCategoryMaster.new', {
                parent: 'itemSubCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCategoryMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-dialog.html',
                        controller: 'ItemSubCategoryMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('itemSubCategoryMaster.edit', {
                parent: 'itemSubCategoryMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCategoryMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemSubCategoryMaster/itemSubCategoryMaster-dialog.html',
                        controller: 'ItemSubCategoryMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
