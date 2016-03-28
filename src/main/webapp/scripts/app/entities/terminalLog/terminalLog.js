'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('terminalLog', {
                parent: 'entity',
                url: '/terminalLogs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TerminalLogs'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/terminalLog/terminalLogs.html',
                        controller: 'TerminalLogController'
                    }
                },
                resolve: {
                }
            })
            .state('terminalLog.detail', {
                parent: 'entity',
                url: '/terminalLog/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TerminalLog'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/terminalLog/terminalLog-detail.html',
                        controller: 'TerminalLogDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'TerminalLog', function($stateParams, TerminalLog) {
                        return TerminalLog.get({id : $stateParams.id});
                    }]
                }
            })
            .state('terminalLog.new', {
                parent: 'terminalLog',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TerminalLogs'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/terminalLog/terminalLog-dialog.html',
                        controller: 'TerminalLogDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('terminalLog.edit', {
                parent: 'terminalLog',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'TerminalLogs'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/terminalLog/terminalLog-dialog.html',
                        controller: 'TerminalLogDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('terminalLog.new', {
                parent: 'terminalLog',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminalLog/terminalLog-dialog.html',
                        controller: 'TerminalLogDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    lastModified: null,
                                    modifiedBy: null,
                                    userId: null,
                                    bankDepositDate: null,
                                    beforeUpdate: null,
                                    afterUpdate: null,
                                    mrCode: null,
                                    remark: null,
                                    txnType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('terminalLog', null, { reload: true });
                    }, function() {
                        $state.go('terminalLog');
                    })
                }]
            })
            .state('terminalLog.edit', {
                parent: 'terminalLog',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminalLog/terminalLog-dialog.html',
                        controller: 'TerminalLogDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TerminalLog', function(TerminalLog) {
                                return TerminalLog.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('terminalLog', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('terminalLog.delete', {
                parent: 'terminalLog',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/terminalLog/terminalLog-delete-dialog.html',
                        controller: 'TerminalLogDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TerminalLog', function(TerminalLog) {
                                return TerminalLog.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('terminalLog', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
