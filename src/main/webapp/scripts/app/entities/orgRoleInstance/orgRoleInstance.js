'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('orgRoleInstance', {
                parent: 'entity',
                url: '/orgRoleInstances',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRoleInstances'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRoleInstance/orgRoleInstances.html',
                        controller: 'OrgRoleInstanceController'
                    }
                },
                resolve: {
                }
            })
            .state('orgRoleInstance.detail', {
                parent: 'entity',
                url: '/orgRoleInstance/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRoleInstance'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRoleInstance/orgRoleInstance-detail.html',
                        controller: 'OrgRoleInstanceDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OrgRoleInstance', function($stateParams, OrgRoleInstance) {
                        return OrgRoleInstance.get({id : $stateParams.id});
                    }]
                }
            })
            .state('orgRoleInstance.new', {
                parent: 'orgRoleInstance',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleInstance/orgRoleInstance-dialog.html',
                        controller: 'OrgRoleInstanceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    orgRoleName: null,
                                    parentOrgRoleId: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    isHead: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleInstance', null, { reload: true });
                    }, function() {
                        $state.go('orgRoleInstance');
                    })
                }]
            })
            .state('orgRoleInstance.edit', {
                parent: 'orgRoleInstance',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleInstance/orgRoleInstance-dialog.html',
                        controller: 'OrgRoleInstanceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OrgRoleInstance', function(OrgRoleInstance) {
                                return OrgRoleInstance.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleInstance', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('orgRoleInstance.delete', {
                parent: 'orgRoleInstance',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRoleInstance/orgRoleInstance-delete-dialog.html',
                        controller: 'OrgRoleInstanceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OrgRoleInstance', function(OrgRoleInstance) {
                                return OrgRoleInstance.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRoleInstance', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
