'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('orgRoleHierarchy', {
                parent: 'entity',
                url: '/orgRoleHierarchys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRoleHierarchys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRoleHierarchy/orgRoleHierarchys.html',
                        controller: 'OrgRoleHierarchyController'
                    }
                },
                resolve: {
                }
            })
            .state('orgRoleHierarchy.detail', {
                parent: 'entity',
                url: '/orgRoleHierarchy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRoleHierarchy'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRoleHierarchy/orgRoleHierarchy-detail.html',
                        controller: 'OrgRoleHierarchyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OrgRoleHierarchy', function($stateParams, OrgRoleHierarchy) {
                        return OrgRoleHierarchy.get({id : $stateParams.id});
                    }]
                }
            })
            .state('orgRoleHierarchy.new', {
                parent: 'orgRoleHierarchy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleHierarchy/orgRoleHierarchy-dialog.html',
                        controller: 'OrgRoleHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    roleHierarchyName: null,
                                    parentRoleHierarchyId: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('orgRoleHierarchy');
                    })
                }]
            })
            .state('orgRoleHierarchy.edit', {
                parent: 'orgRoleHierarchy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleHierarchy/orgRoleHierarchy-dialog.html',
                        controller: 'OrgRoleHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OrgRoleHierarchy', function(OrgRoleHierarchy) {
                                return OrgRoleHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('orgRoleHierarchy.delete', {
                parent: 'orgRoleHierarchy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleHierarchy/orgRoleHierarchy-delete-dialog.html',
                        controller: 'OrgRoleHierarchyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OrgRoleHierarchy', function(OrgRoleHierarchy) {
                                return OrgRoleHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
