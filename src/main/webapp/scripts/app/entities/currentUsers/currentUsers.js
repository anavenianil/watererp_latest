'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('currentUsers', {
                parent: 'entity',
                url: '/currentUserss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CurrentUserss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/currentUsers/currentUserss.html',
                        controller: 'CurrentUsersController'
                    }
                },
                resolve: {
                }
            })
            .state('currentUsers.detail', {
                parent: 'entity',
                url: '/currentUsers/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CurrentUsers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/currentUsers/currentUsers-detail.html',
                        controller: 'CurrentUsersDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CurrentUsers', function($stateParams, CurrentUsers) {
                        return CurrentUsers.get({id : $stateParams.id});
                    }]
                }
            })
            .state('currentUsers.new', {
                parent: 'currentUsers',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CurrentUserss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/currentUsers/currentUsers-dialog.html',
                        controller: 'CurrentUsersDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('currentUsers.edit', {
                parent: 'currentUsers',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CurrentUserss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/currentUsers/currentUsers-dialog.html',
                        controller: 'CurrentUsersDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('currentUsers.new', {
                parent: 'currentUsers',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/currentUsers/currentUsers-dialog.html',
                        controller: 'CurrentUsersDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    terminalId: null,
                                    meterReaderId: null,
                                    userId: null,
                                    requestType: null,
                                    loginTime: null,
                                    ip: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('currentUsers', null, { reload: true });
                    }, function() {
                        $state.go('currentUsers');
                    })
                }]
            })
            .state('currentUsers.edit', {
                parent: 'currentUsers',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/currentUsers/currentUsers-dialog.html',
                        controller: 'CurrentUsersDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CurrentUsers', function(CurrentUsers) {
                                return CurrentUsers.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('currentUsers', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('currentUsers.delete', {
                parent: 'currentUsers',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/currentUsers/currentUsers-delete-dialog.html',
                        controller: 'CurrentUsersDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CurrentUsers', function(CurrentUsers) {
                                return CurrentUsers.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('currentUsers', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
