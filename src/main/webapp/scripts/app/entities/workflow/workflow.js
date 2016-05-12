'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflow', {
                parent: 'entity',
                url: '/workflows',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Workflows'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflow/workflows.html',
                        controller: 'WorkflowController'
                    }
                },
                resolve: {
                }
            })
            .state('workflow.detail', {
                parent: 'entity',
                url: '/workflow/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Workflow'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflow/workflow-detail.html',
                        controller: 'WorkflowDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Workflow', function($stateParams, Workflow) {
                        return Workflow.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('workflow.new', {
                parent: 'workflow',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflow/workflow-dialog.html',
                        controller: 'WorkflowDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    stageId: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workflow', null, { reload: true });
                    }, function() {
                        $state.go('workflow');
                    })
                }]
            })*/
            /*.state('workflow.edit', {
                parent: 'workflow',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflow/workflow-dialog.html',
                        controller: 'WorkflowDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Workflow', function(Workflow) {
                                return Workflow.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflow', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('workflow.delete', {
                parent: 'workflow',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflow/workflow-delete-dialog.html',
                        controller: 'WorkflowDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Workflow', function(Workflow) {
                                return Workflow.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflow', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflow.new', {
                parent: 'workflow',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Workflows'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/workflow/workflow-dialog.html',
                        controller: 'WorkflowDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('workflow.edit', {
                parent: 'workflow',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Workflows'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/workflow/workflow-dialog.html',
                        controller: 'WorkflowDialogController'
                    }
                },
                resolve: {
                }
            });
    });
