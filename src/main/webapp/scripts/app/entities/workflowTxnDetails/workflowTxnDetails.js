'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workflowTxnDetails', {
                parent: 'entity',
                url: '/workflowTxnDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowTxnDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowTxnDetails/workflowTxnDetailss.html',
                        controller: 'WorkflowTxnDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('workflowTxnDetails.detail', {
                parent: 'entity',
                url: '/workflowTxnDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WorkflowTxnDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workflowTxnDetails/workflowTxnDetails-detail.html',
                        controller: 'WorkflowTxnDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WorkflowTxnDetails', function($stateParams, WorkflowTxnDetails) {
                        return WorkflowTxnDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workflowTxnDetails.new', {
                parent: 'workflowTxnDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTxnDetails/workflowTxnDetails-dialog.html',
                        controller: 'WorkflowTxnDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    requestId: null,
                                    referenceNumber: null,
                                    rowNumber: null,
                                    columnName: null,
                                    previousValue: null,
                                    newValue: null,
                                    ipAddress: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTxnDetails', null, { reload: true });
                    }, function() {
                        $state.go('workflowTxnDetails');
                    })
                }]
            })
            .state('workflowTxnDetails.edit', {
                parent: 'workflowTxnDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTxnDetails/workflowTxnDetails-dialog.html',
                        controller: 'WorkflowTxnDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkflowTxnDetails', function(WorkflowTxnDetails) {
                                return WorkflowTxnDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTxnDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workflowTxnDetails.delete', {
                parent: 'workflowTxnDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workflowTxnDetails/workflowTxnDetails-delete-dialog.html',
                        controller: 'WorkflowTxnDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkflowTxnDetails', function(WorkflowTxnDetails) {
                                return WorkflowTxnDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workflowTxnDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
