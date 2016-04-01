'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('terminal', {
                parent: 'entity',
                url: '/terminals',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Terminals'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/terminal/terminals.html',
                        controller: 'TerminalController'
                    }
                },
                resolve: {
                }
            })
            .state('terminal.detail', {
                parent: 'entity',
                url: '/terminal/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Terminal'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/terminal/terminal-detail.html',
                        controller: 'TerminalDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Terminal', function($stateParams, Terminal) {
                        return Terminal.get({id : $stateParams.id});
                    }]
                }
            })
            .state('terminal.new', {
                parent: 'terminal',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Terminals'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/terminal/terminal-dialog.html',
                        controller: 'TerminalDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('terminal.edit', {
                parent: 'terminal',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Terminals'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/terminal/terminal-dialog.html',
                        controller: 'TerminalDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('terminal.new', {
                parent: 'terminal',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminal/terminal-dialog.html',
                        controller: 'TerminalDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    status: null,
                                    userId: null,
                                    mrCode: null,
                                    secCode: null,
                                    divCode: null,
                                    secName: null,
                                    userName: null,
                                    mobileNo: null,
                                    ver: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('terminal', null, { reload: true });
                    }, function() {
                        $state.go('terminal');
                    })
                }]
            })
            .state('terminal.edit', {
                parent: 'terminal',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminal/terminal-dialog.html',
                        controller: 'TerminalDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Terminal', function(Terminal) {
                                return Terminal.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('terminal', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('terminal.delete', {
                parent: 'terminal',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminal/terminal-delete-dialog.html',
                        controller: 'TerminalDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Terminal', function(Terminal) {
                                return Terminal.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('terminal', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
