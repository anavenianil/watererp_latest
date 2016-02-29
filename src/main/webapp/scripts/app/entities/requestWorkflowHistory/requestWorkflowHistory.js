'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('requestWorkflowHistory', {
                parent: 'entity',
                url: '/requestWorkflowHistorys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestWorkflowHistorys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestWorkflowHistory/requestWorkflowHistorys.html',
                        controller: 'RequestWorkflowHistoryController'
                    }
                },
                resolve: {
                }
            })
            .state('requestWorkflowHistory.detail', {
                parent: 'entity',
                url: '/requestWorkflowHistory/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestWorkflowHistory'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestWorkflowHistory/requestWorkflowHistory-detail.html',
                        controller: 'RequestWorkflowHistoryDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RequestWorkflowHistory', function($stateParams, RequestWorkflowHistory) {
                        return RequestWorkflowHistory.get({id : $stateParams.id});
                    }]
                }
            })
            .state('requestWorkflowHistory.new', {
                parent: 'requestWorkflowHistory',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowHistory/requestWorkflowHistory-dialog.html',
                        controller: 'RequestWorkflowHistoryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    requestStage: null,
                                    assignedDate: null,
                                    actionedDate: null,
                                    remarks: null,
                                    ipAddress: null,
                                    assignedRole: null,
                                    domainObject: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('requestWorkflowHistory', null, { reload: true });
                    }, function() {
                        $state.go('requestWorkflowHistory');
                    })
                }]
            })
            .state('requestWorkflowHistory.edit', {
                parent: 'requestWorkflowHistory',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowHistory/requestWorkflowHistory-dialog.html',
                        controller: 'RequestWorkflowHistoryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RequestWorkflowHistory', function(RequestWorkflowHistory) {
                                return RequestWorkflowHistory.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestWorkflowHistory', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('requestWorkflowHistory.delete', {
                parent: 'requestWorkflowHistory',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowHistory/requestWorkflowHistory-delete-dialog.html',
                        controller: 'RequestWorkflowHistoryDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RequestWorkflowHistory', function(RequestWorkflowHistory) {
                                return RequestWorkflowHistory.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestWorkflowHistory', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
