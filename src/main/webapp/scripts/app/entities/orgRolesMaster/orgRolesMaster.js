'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('orgRolesMaster', {
                parent: 'entity',
                url: '/orgRolesMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRolesMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRolesMaster/orgRolesMasters.html',
                        controller: 'OrgRolesMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('orgRolesMaster.detail', {
                parent: 'entity',
                url: '/orgRolesMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgRolesMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgRolesMaster/orgRolesMaster-detail.html',
                        controller: 'OrgRolesMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OrgRolesMaster', function($stateParams, OrgRolesMaster) {
                        return OrgRolesMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('orgRolesMaster.new', {
                parent: 'orgRolesMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRolesMaster/orgRolesMaster-dialog.html',
                        controller: 'OrgRolesMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    orgRoleName: null,
                                    hierarchyId: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orgRolesMaster', null, { reload: true });
                    }, function() {
                        $state.go('orgRolesMaster');
                    })
                }]
            })
            .state('orgRolesMaster.edit', {
                parent: 'orgRolesMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRolesMaster/orgRolesMaster-dialog.html',
                        controller: 'OrgRolesMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OrgRolesMaster', function(OrgRolesMaster) {
                                return OrgRolesMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRolesMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('orgRolesMaster.delete', {
                parent: 'orgRolesMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgRolesMaster/orgRolesMaster-delete-dialog.html',
                        controller: 'OrgRolesMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OrgRolesMaster', function(OrgRolesMaster) {
                                return OrgRolesMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgRolesMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
