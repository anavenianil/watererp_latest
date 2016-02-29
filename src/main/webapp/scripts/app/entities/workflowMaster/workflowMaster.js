'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowMaster', {
                parent: 'entity',
                url: '/workflowMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowMaster/workflowMasters.html',
                        controller: 'WorkflowMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowMaster.detail', {
                parent: 'entity',
                url: '/workflowMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowMaster/workflowMaster-detail.html',
                        controller: 'WorkflowMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowMaster', function($stateParams, WorkflowMaster) {
                        return WorkflowMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowMaster.new', {
                parent: 'workflowMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowMaster/workflowMaster-dialog.html',
                        controller: 'WorkflowMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    workflowName: null,
                                    toWorkflow: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workflowMaster', null, { reload: true });
                    }, function() {
                        $state.go('workflowMaster');
                    })
                }]
            })
            .state('workflowMaster.edit', {
                parent: 'workflowMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowMaster/workflowMaster-dialog.html',
                        controller: 'WorkflowMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowMaster', function(WorkflowMaster) {
                                return WorkflowMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowMaster.delete', {
                parent: 'workflowMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowMaster/workflowMaster-delete-dialog.html',
                        controller: 'WorkflowMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowMaster', function(WorkflowMaster) {
                                return WorkflowMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
