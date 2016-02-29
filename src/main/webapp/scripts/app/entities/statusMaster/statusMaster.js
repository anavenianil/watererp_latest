'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('statusMaster', {
                parent: 'entity',
                url: '/statusMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'StatusMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/statusMaster/statusMasters.html',
                        controller: 'StatusMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('statusMaster.detail', {
                parent: 'entity',
                url: '/statusMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'StatusMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/statusMaster/statusMaster-detail.html',
                        controller: 'StatusMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'StatusMaster', function($stateParams, StatusMaster) {
                        return StatusMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('statusMaster.new', {
                parent: 'statusMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/statusMaster/statusMaster-dialog.html',
                        controller: 'StatusMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    status: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('statusMaster', null, { reload: true });
                    }, function() {
                        $state.go('statusMaster');
                    })
                }]
            })
            .state('statusMaster.edit', {
                parent: 'statusMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/statusMaster/statusMaster-dialog.html',
                        controller: 'StatusMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['StatusMaster', function(StatusMaster) {
                                return StatusMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('statusMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('statusMaster.delete', {
                parent: 'statusMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/statusMaster/statusMaster-delete-dialog.html',
                        controller: 'StatusMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['StatusMaster', function(StatusMaster) {
                                return StatusMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('statusMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
