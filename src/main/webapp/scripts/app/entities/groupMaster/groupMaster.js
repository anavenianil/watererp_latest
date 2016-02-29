'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('groupMaster', {
                parent: 'entity',
                url: '/groupMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'GroupMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/groupMaster/groupMasters.html',
                        controller: 'GroupMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('groupMaster.detail', {
                parent: 'entity',
                url: '/groupMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'GroupMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/groupMaster/groupMaster-detail.html',
                        controller: 'GroupMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'GroupMaster', function($stateParams, GroupMaster) {
                        return GroupMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('groupMaster.new', {
                parent: 'groupMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/groupMaster/groupMaster-dialog.html',
                        controller: 'GroupMasterDialogController',
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
                        $state.go('groupMaster', null, { reload: true });
                    }, function() {
                        $state.go('groupMaster');
                    })
                }]
            })
            .state('groupMaster.edit', {
                parent: 'groupMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/groupMaster/groupMaster-dialog.html',
                        controller: 'GroupMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['GroupMaster', function(GroupMaster) {
                                return GroupMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('groupMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('groupMaster.delete', {
                parent: 'groupMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/groupMaster/groupMaster-delete-dialog.html',
                        controller: 'GroupMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['GroupMaster', function(GroupMaster) {
                                return GroupMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('groupMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
