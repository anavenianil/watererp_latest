'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowTypeMaster', {
                parent: 'entity',
                url: '/workflowTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowTypeMaster/workflowTypeMasters.html',
                        controller: 'WorkflowTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowTypeMaster.detail', {
                parent: 'entity',
                url: '/workflowTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowTypeMaster/workflowTypeMaster-detail.html',
                        controller: 'WorkflowTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowTypeMaster', function($stateParams, WorkflowTypeMaster) {
                        return WorkflowTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowTypeMaster.new', {
                parent: 'workflowTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTypeMaster/workflowTypeMaster-dialog.html',
                        controller: 'WorkflowTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('workflowTypeMaster');
                    })
                }]
            })
            .state('workflowTypeMaster.edit', {
                parent: 'workflowTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTypeMaster/workflowTypeMaster-dialog.html',
                        controller: 'WorkflowTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowTypeMaster', function(WorkflowTypeMaster) {
                                return WorkflowTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowTypeMaster.delete', {
                parent: 'workflowTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTypeMaster/workflowTypeMaster-delete-dialog.html',
                        controller: 'WorkflowTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowTypeMaster', function(WorkflowTypeMaster) {
                                return WorkflowTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
