'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reqDesigWorkflowMapping', {
                parent: 'entity',
                url: '/reqDesigWorkflowMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReqDesigWorkflowMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reqDesigWorkflowMapping/reqDesigWorkflowMappings.html',
                        controller: 'ReqDesigWorkflowMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('reqDesigWorkflowMapping.detail', {
                parent: 'entity',
                url: '/reqDesigWorkflowMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReqDesigWorkflowMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reqDesigWorkflowMapping/reqDesigWorkflowMapping-detail.html',
                        controller: 'ReqDesigWorkflowMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ReqDesigWorkflowMapping', function($stateParams, ReqDesigWorkflowMapping) {
                        return ReqDesigWorkflowMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reqDesigWorkflowMapping.new', {
                parent: 'reqDesigWorkflowMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqDesigWorkflowMapping/reqDesigWorkflowMapping-dialog.html',
                        controller: 'ReqDesigWorkflowMappingDialogController',
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
                        $state.go('reqDesigWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('reqDesigWorkflowMapping');
                    })
                }]
            })
            .state('reqDesigWorkflowMapping.edit', {
                parent: 'reqDesigWorkflowMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqDesigWorkflowMapping/reqDesigWorkflowMapping-dialog.html',
                        controller: 'ReqDesigWorkflowMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ReqDesigWorkflowMapping', function(ReqDesigWorkflowMapping) {
                                return ReqDesigWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reqDesigWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('reqDesigWorkflowMapping.delete', {
                parent: 'reqDesigWorkflowMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reqDesigWorkflowMapping/reqDesigWorkflowMapping-delete-dialog.html',
                        controller: 'ReqDesigWorkflowMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ReqDesigWorkflowMapping', function(ReqDesigWorkflowMapping) {
                                return ReqDesigWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reqDesigWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
