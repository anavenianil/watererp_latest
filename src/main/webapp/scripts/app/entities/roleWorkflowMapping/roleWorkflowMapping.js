'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('roleWorkflowMapping', {
                parent: 'entity',
                url: '/roleWorkflowMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RoleWorkflowMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/roleWorkflowMapping/roleWorkflowMappings.html',
                        controller: 'RoleWorkflowMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('roleWorkflowMapping.detail', {
                parent: 'entity',
                url: '/roleWorkflowMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RoleWorkflowMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/roleWorkflowMapping/roleWorkflowMapping-detail.html',
                        controller: 'RoleWorkflowMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RoleWorkflowMapping', function($stateParams, RoleWorkflowMapping) {
                        return RoleWorkflowMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('roleWorkflowMapping.new', {
                parent: 'roleWorkflowMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/roleWorkflowMapping/roleWorkflowMapping-dialog.html',
                        controller: 'RoleWorkflowMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('roleWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('roleWorkflowMapping');
                    })
                }]
            })
            .state('roleWorkflowMapping.edit', {
                parent: 'roleWorkflowMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/roleWorkflowMapping/roleWorkflowMapping-dialog.html',
                        controller: 'RoleWorkflowMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RoleWorkflowMapping', function(RoleWorkflowMapping) {
                                return RoleWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('roleWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('roleWorkflowMapping.delete', {
                parent: 'roleWorkflowMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/roleWorkflowMapping/roleWorkflowMapping-delete-dialog.html',
                        controller: 'RoleWorkflowMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RoleWorkflowMapping', function(RoleWorkflowMapping) {
                                return RoleWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('roleWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
