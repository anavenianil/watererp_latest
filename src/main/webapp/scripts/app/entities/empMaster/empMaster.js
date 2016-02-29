'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('empMaster', {
                parent: 'entity',
                url: '/empMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/empMaster/empMasters.html',
                        controller: 'EmpMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('empMaster.detail', {
                parent: 'entity',
                url: '/empMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'EmpMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/empMaster/empMaster-detail.html',
                        controller: 'EmpMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'EmpMaster', function($stateParams, EmpMaster) {
                        return EmpMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('empMaster.new', {
                parent: 'empMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empMaster/empMaster-dialog.html',
                        controller: 'EmpMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('empMaster', null, { reload: true });
                    }, function() {
                        $state.go('empMaster');
                    })
                }]
            })
            .state('empMaster.edit', {
                parent: 'empMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empMaster/empMaster-dialog.html',
                        controller: 'EmpMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['EmpMaster', function(EmpMaster) {
                                return EmpMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('empMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('empMaster.delete', {
                parent: 'empMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/empMaster/empMaster-delete-dialog.html',
                        controller: 'EmpMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['EmpMaster', function(EmpMaster) {
                                return EmpMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('empMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
