'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('departmentsMaster', {
                parent: 'entity',
                url: '/departmentsMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentsMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentsMaster/departmentsMasters.html',
                        controller: 'DepartmentsMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('departmentsMaster.detail', {
                parent: 'entity',
                url: '/departmentsMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DepartmentsMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/departmentsMaster/departmentsMaster-detail.html',
                        controller: 'DepartmentsMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DepartmentsMaster', function($stateParams, DepartmentsMaster) {
                        return DepartmentsMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('departmentsMaster.new', {
                parent: 'departmentsMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsMaster/departmentsMaster-dialog.html',
                        controller: 'DepartmentsMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    departmentName: null,
                                    parentDeparment: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsMaster', null, { reload: true });
                    }, function() {
                        $state.go('departmentsMaster');
                    })
                }]
            })
            .state('departmentsMaster.edit', {
                parent: 'departmentsMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsMaster/departmentsMaster-dialog.html',
                        controller: 'DepartmentsMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DepartmentsMaster', function(DepartmentsMaster) {
                                return DepartmentsMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('departmentsMaster.delete', {
                parent: 'departmentsMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/departmentsMaster/departmentsMaster-delete-dialog.html',
                        controller: 'DepartmentsMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DepartmentsMaster', function(DepartmentsMaster) {
                                return DepartmentsMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('departmentsMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
