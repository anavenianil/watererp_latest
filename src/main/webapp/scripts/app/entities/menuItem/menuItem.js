'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('menuItem', {
                parent: 'entity',
                url: '/menuItems',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuItems'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuItem/menuItems.html',
                        controller: 'MenuItemController'
                    }
                },
                resolve: {
                }
            })
            .state('menuItem.detail', {
                parent: 'entity',
                url: '/menuItem/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuItem'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuItem/menuItem-detail.html',
                        controller: 'MenuItemDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MenuItem', function($stateParams, MenuItem) {
                        return MenuItem.get({id : $stateParams.id});
                    }]
                }
            })
            .state('menuItem.new', {
                parent: 'menuItem',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem/menuItem-dialog.html',
                        controller: 'MenuItemDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    path: null,
                                    modifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem', null, { reload: true });
                    }, function() {
                        $state.go('menuItem');
                    })
                }]
            })
            .state('menuItem.edit', {
                parent: 'menuItem',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem/menuItem-dialog.html',
                        controller: 'MenuItemDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MenuItem', function(MenuItem) {
                                return MenuItem.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('menuItem.delete', {
                parent: 'menuItem',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem/menuItem-delete-dialog.html',
                        controller: 'MenuItemDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MenuItem', function(MenuItem) {
                                return MenuItem.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
