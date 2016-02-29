'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('requestWorkflowMapping', {
                parent: 'entity',
                url: '/requestWorkflowMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestWorkflowMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestWorkflowMapping/requestWorkflowMappings.html',
                        controller: 'RequestWorkflowMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('requestWorkflowMapping.detail', {
                parent: 'entity',
                url: '/requestWorkflowMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestWorkflowMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestWorkflowMapping/requestWorkflowMapping-detail.html',
                        controller: 'RequestWorkflowMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RequestWorkflowMapping', function($stateParams, RequestWorkflowMapping) {
                        return RequestWorkflowMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('requestWorkflowMapping.new', {
                parent: 'requestWorkflowMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowMapping/requestWorkflowMapping-dialog.html',
                        controller: 'RequestWorkflowMappingDialogController',
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
                        $state.go('requestWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('requestWorkflowMapping');
                    })
                }]
            })
            .state('requestWorkflowMapping.edit', {
                parent: 'requestWorkflowMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowMapping/requestWorkflowMapping-dialog.html',
                        controller: 'RequestWorkflowMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RequestWorkflowMapping', function(RequestWorkflowMapping) {
                                return RequestWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('requestWorkflowMapping.delete', {
                parent: 'requestWorkflowMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestWorkflowMapping/requestWorkflowMapping-delete-dialog.html',
                        controller: 'RequestWorkflowMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RequestWorkflowMapping', function(RequestWorkflowMapping) {
                                return RequestWorkflowMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestWorkflowMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
