'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('url2Role', {
                parent: 'entity',
                url: '/url2Roles',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Url2Roles'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/url2Role/url2Roles.html',
                        controller: 'Url2RoleController'
                    }
                },
                resolve: {
                }
            })
            .state('url2Role.detail', {
                parent: 'entity',
                url: '/url2Role/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Url2Role'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/url2Role/url2Role-detail.html',
                        controller: 'Url2RoleDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Url2Role', function($stateParams, Url2Role) {
                        return Url2Role.get({id : $stateParams.id});
                    }]
                }
            })
            .state('url2Role.new', {
                parent: 'url2Role',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url2Role/url2Role-dialog.html',
                        controller: 'Url2RoleDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('url2Role', null, { reload: true });
                    }, function() {
                        $state.go('url2Role');
                    })
                }]
            })
            .state('url2Role.edit', {
                parent: 'url2Role',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url2Role/url2Role-dialog.html',
                        controller: 'Url2RoleDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Url2Role', function(Url2Role) {
                                return Url2Role.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('url2Role', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('url2Role.delete', {
                parent: 'url2Role',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url2Role/url2Role-delete-dialog.html',
                        controller: 'Url2RoleDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Url2Role', function(Url2Role) {
                                return Url2Role.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('url2Role', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
