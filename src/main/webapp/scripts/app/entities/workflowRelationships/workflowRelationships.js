'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowRelationships', {
                parent: 'entity',
                url: '/workflowRelationshipss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowRelationshipss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowRelationships/workflowRelationshipss.html',
                        controller: 'WorkflowRelationshipsController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowRelationships.detail', {
                parent: 'entity',
                url: '/workflowRelationships/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowRelationships'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowRelationships/workflowRelationships-detail.html',
                        controller: 'WorkflowRelationshipsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowRelationships', function($stateParams, WorkflowRelationships) {
                        return WorkflowRelationships.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowRelationships.new', {
                parent: 'workflowRelationships',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelationships/workflowRelationships-dialog.html',
                        controller: 'WorkflowRelationshipsDialogController',
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
                        $state.go('workflowRelationships', null, { reload: true });
                    }, function() {
                        $state.go('workflowRelationships');
                    })
                }]
            })
            .state('workflowRelationships.edit', {
                parent: 'workflowRelationships',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelationships/workflowRelationships-dialog.html',
                        controller: 'WorkflowRelationshipsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowRelationships', function(WorkflowRelationships) {
                                return WorkflowRelationships.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowRelationships', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowRelationships.delete', {
                parent: 'workflowRelationships',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowRelationships/workflowRelationships-delete-dialog.html',
                        controller: 'WorkflowRelationshipsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowRelationships', function(WorkflowRelationships) {
                                return WorkflowRelationships.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowRelationships', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
