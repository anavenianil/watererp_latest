'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reqOrgWorkflowMapping', {
                parent: 'entity',
                url: '/reqOrgWorkflowMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReqOrgWorkflowMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reqOrgWorkflowMapping/reqOrgWorkflowMappings.html',
                        controller: 'ReqOrgWorkflowMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('reqOrgWorkflowMapping.detail', {
                parent: 'entity',
                url: '/reqOrgWorkflowMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReqOrgWorkflowMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reqOrgWorkflowMapping/reqOrgWorkflowMapping-detail.html',
                        controller: 'ReqOrgWorkflowMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ReqOrgWorkflowMapping', function($stateParams, ReqOrgWorkflowMapping) {
                        return ReqOrgWorkflowMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reqOrgWorkflowMapping.new', {
                parent: 'reqOrgWorkflowMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqOrgWorkflowMapping/reqOrgWorkflowMapping-dialog.html',
                        controller: 'ReqOrgWorkflowMappingDialogController',
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
                        $state.go('reqOrgWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('reqOrgWorkflowMapping');
                    })
                }]
            })
            .state('reqOrgWorkflowMapping.edit', {
                parent: 'reqOrgWorkflowMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqOrgWorkflowMapping/reqOrgWorkflowMapping-dialog.html',
                        controller: 'ReqOrgWorkflowMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ReqOrgWorkflowMapping', function(ReqOrgWorkflowMapping) {
                                return ReqOrgWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reqOrgWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('reqOrgWorkflowMapping.delete', {
                parent: 'reqOrgWorkflowMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqOrgWorkflowMapping/reqOrgWorkflowMapping-delete-dialog.html',
                        controller: 'ReqOrgWorkflowMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ReqOrgWorkflowMapping', function(ReqOrgWorkflowMapping) {
                                return ReqOrgWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reqOrgWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
