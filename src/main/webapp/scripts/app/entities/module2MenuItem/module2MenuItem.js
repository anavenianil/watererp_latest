'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('module2MenuItem', {
                parent: 'entity',
                url: '/module2MenuItems',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Module2MenuItems'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/module2MenuItem/module2MenuItems.html',
                        controller: 'Module2MenuItemController'
                    }
                },
                resolve: {
                }
            })
            .state('module2MenuItem.detail', {
                parent: 'entity',
                url: '/module2MenuItem/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Module2MenuItem'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/module2MenuItem/module2MenuItem-detail.html',
                        controller: 'Module2MenuItemDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Module2MenuItem', function($stateParams, Module2MenuItem) {
                        return Module2MenuItem.get({id : $stateParams.id});
                    }]
                }
            })
            .state('module2MenuItem.new', {
                parent: 'module2MenuItem',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/module2MenuItem/module2MenuItem-dialog.html',
                        controller: 'Module2MenuItemDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    priority: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('module2MenuItem', null, { reload: true });
                    }, function() {
                        $state.go('module2MenuItem');
                    })
                }]
            })
            .state('module2MenuItem.edit', {
                parent: 'module2MenuItem',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/module2MenuItem/module2MenuItem-dialog.html',
                        controller: 'Module2MenuItemDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Module2MenuItem', function(Module2MenuItem) {
                                return Module2MenuItem.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('module2MenuItem', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('module2MenuItem.delete', {
                parent: 'module2MenuItem',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/module2MenuItem/module2MenuItem-delete-dialog.html',
                        controller: 'Module2MenuItemDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Module2MenuItem', function(Module2MenuItem) {
                                return Module2MenuItem.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('module2MenuItem', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
