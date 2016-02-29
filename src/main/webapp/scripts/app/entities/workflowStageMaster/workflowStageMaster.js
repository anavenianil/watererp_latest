'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowStageMaster', {
                parent: 'entity',
                url: '/workflowStageMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowStageMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowStageMaster/workflowStageMasters.html',
                        controller: 'WorkflowStageMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowStageMaster.detail', {
                parent: 'entity',
                url: '/workflowStageMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowStageMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowStageMaster/workflowStageMaster-detail.html',
                        controller: 'WorkflowStageMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowStageMaster', function($stateParams, WorkflowStageMaster) {
                        return WorkflowStageMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowStageMaster.new', {
                parent: 'workflowStageMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowStageMaster/workflowStageMaster-dialog.html',
                        controller: 'WorkflowStageMasterDialogController',
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
                        $state.go('workflowStageMaster', null, { reload: true });
                    }, function() {
                        $state.go('workflowStageMaster');
                    })
                }]
            })
            .state('workflowStageMaster.edit', {
                parent: 'workflowStageMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowStageMaster/workflowStageMaster-dialog.html',
                        controller: 'WorkflowStageMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowStageMaster', function(WorkflowStageMaster) {
                                return WorkflowStageMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowStageMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowStageMaster.delete', {
                parent: 'workflowStageMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowStageMaster/workflowStageMaster-delete-dialog.html',
                        controller: 'WorkflowStageMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowStageMaster', function(WorkflowStageMaster) {
                                return WorkflowStageMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowStageMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
