'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('orgHierarchy', {
                parent: 'entity',
                url: '/orgHierarchys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgHierarchys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgHierarchy/orgHierarchys.html',
                        controller: 'OrgHierarchyController'
                    }
                },
                resolve: {
                }
            })
            .state('orgHierarchy.detail', {
                parent: 'entity',
                url: '/orgHierarchy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OrgHierarchy'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/orgHierarchy/orgHierarchy-detail.html',
                        controller: 'OrgHierarchyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OrgHierarchy', function($stateParams, OrgHierarchy) {
                        return OrgHierarchy.get({id : $stateParams.id});
                    }]
                }
            })
            .state('orgHierarchy.new', {
                parent: 'orgHierarchy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgHierarchy/orgHierarchy-dialog.html',
                        controller: 'OrgHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    hierarchyName: null,
                                    parentHierarchyId: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('orgHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('orgHierarchy');
                    })
                }]
            })
            .state('orgHierarchy.edit', {
                parent: 'orgHierarchy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgHierarchy/orgHierarchy-dialog.html',
                        controller: 'OrgHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OrgHierarchy', function(OrgHierarchy) {
                                return OrgHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('orgHierarchy.delete', {
                parent: 'orgHierarchy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/orgHierarchy/orgHierarchy-delete-dialog.html',
                        controller: 'OrgHierarchyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OrgHierarchy', function(OrgHierarchy) {
                                return OrgHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('orgHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
