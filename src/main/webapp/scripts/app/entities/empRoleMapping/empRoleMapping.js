'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('empRoleMapping', {
                parent: 'entity',
                url: '/empRoleMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpRoleMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMappings.html',
                        controller: 'EmpRoleMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('empRoleMapping.detail', {
                parent: 'entity',
                url: '/empRoleMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpRoleMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-detail.html',
                        controller: 'EmpRoleMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'EmpRoleMapping', function($stateParams, EmpRoleMapping) {
                        return EmpRoleMapping.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('empRoleMapping.new', {
                parent: 'empRoleMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-dialog.html',
                        controller: 'EmpRoleMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    internalDivision: null,
                                    internalRole: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    parentRoleId: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('empRoleMapping', null, { reload: true });
                    }, function() {
                        $state.go('empRoleMapping');
                    })
                }]
            })*/
            /*.state('empRoleMapping.edit', {
                parent: 'empRoleMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-dialog.html',
                        controller: 'EmpRoleMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['EmpRoleMapping', function(EmpRoleMapping) {
                                return EmpRoleMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('empRoleMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('empRoleMapping.delete', {
                parent: 'empRoleMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-delete-dialog.html',
                        controller: 'EmpRoleMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['EmpRoleMapping', function(EmpRoleMapping) {
                                return EmpRoleMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('empRoleMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('empRoleMapping.new', {
                parent: 'empRoleMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpRoleMappings'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-dialog.html',
                        controller: 'EmpRoleMappingDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('empRoleMapping.edit', {
                parent: 'empRoleMapping',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpRoleMappings'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/empRoleMapping/empRoleMapping-dialog.html',
                        controller: 'EmpRoleMappingDialogController'
                    }
                },
                resolve: {
                }
            });
    });
