'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('departmentsHierarchy', {
                parent: 'entity',
                url: '/departmentsHierarchys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentsHierarchys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentsHierarchy/departmentsHierarchys.html',
                        controller: 'DepartmentsHierarchyController'
                    }
                },
                resolve: {
                }
            })
            .state('departmentsHierarchy.detail', {
                parent: 'entity',
                url: '/departmentsHierarchy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentsHierarchy'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentsHierarchy/departmentsHierarchy-detail.html',
                        controller: 'DepartmentsHierarchyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DepartmentsHierarchy', function($stateParams, DepartmentsHierarchy) {
                        return DepartmentsHierarchy.get({id : $stateParams.id});
                    }]
                }
            })
            .state('departmentsHierarchy.new', {
                parent: 'departmentsHierarchy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsHierarchy/departmentsHierarchy-dialog.html',
                        controller: 'DepartmentsHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    deptHierarchyName: null,
                                    parentDeptHierarchyId: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('departmentsHierarchy');
                    })
                }]
            })
            .state('departmentsHierarchy.edit', {
                parent: 'departmentsHierarchy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsHierarchy/departmentsHierarchy-dialog.html',
                        controller: 'DepartmentsHierarchyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DepartmentsHierarchy', function(DepartmentsHierarchy) {
                                return DepartmentsHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('departmentsHierarchy.delete', {
                parent: 'departmentsHierarchy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsHierarchy/departmentsHierarchy-delete-dialog.html',
                        controller: 'DepartmentsHierarchyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DepartmentsHierarchy', function(DepartmentsHierarchy) {
                                return DepartmentsHierarchy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsHierarchy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
