'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('departmentTypeMaster', {
                parent: 'entity',
                url: '/departmentTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentTypeMaster/departmentTypeMasters.html',
                        controller: 'DepartmentTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('departmentTypeMaster.detail', {
                parent: 'entity',
                url: '/departmentTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentTypeMaster/departmentTypeMaster-detail.html',
                        controller: 'DepartmentTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DepartmentTypeMaster', function($stateParams, DepartmentTypeMaster) {
                        return DepartmentTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('departmentTypeMaster.new', {
                parent: 'departmentTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentTypeMaster/departmentTypeMaster-dialog.html',
                        controller: 'DepartmentTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('departmentTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('departmentTypeMaster');
                    })
                }]
            })
            .state('departmentTypeMaster.edit', {
                parent: 'departmentTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentTypeMaster/departmentTypeMaster-dialog.html',
                        controller: 'DepartmentTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DepartmentTypeMaster', function(DepartmentTypeMaster) {
                                return DepartmentTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('departmentTypeMaster.delete', {
                parent: 'departmentTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentTypeMaster/departmentTypeMaster-delete-dialog.html',
                        controller: 'DepartmentTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DepartmentTypeMaster', function(DepartmentTypeMaster) {
                                return DepartmentTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
