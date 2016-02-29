'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowRelations', {
                parent: 'entity',
                url: '/workflowRelationss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowRelationss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowRelations/workflowRelationss.html',
                        controller: 'WorkflowRelationsController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowRelations.detail', {
                parent: 'entity',
                url: '/workflowRelations/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowRelations'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowRelations/workflowRelations-detail.html',
                        controller: 'WorkflowRelationsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowRelations', function($stateParams, WorkflowRelations) {
                        return WorkflowRelations.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowRelations.new', {
                parent: 'workflowRelations',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelations/workflowRelations-dialog.html',
                        controller: 'WorkflowRelationsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workflowRelations', null, { reload: true });
                    }, function() {
                        $state.go('workflowRelations');
                    })
                }]
            })
            .state('workflowRelations.edit', {
                parent: 'workflowRelations',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelations/workflowRelations-dialog.html',
                        controller: 'WorkflowRelationsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowRelations', function(WorkflowRelations) {
                                return WorkflowRelations.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowRelations', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowRelations.delete', {
                parent: 'workflowRelations',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelations/workflowRelations-delete-dialog.html',
                        controller: 'WorkflowRelationsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowRelations', function(WorkflowRelations) {
                                return WorkflowRelations.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowRelations', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
