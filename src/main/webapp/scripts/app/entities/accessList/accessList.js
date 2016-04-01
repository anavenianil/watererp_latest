'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('accessList', {
                parent: 'entity',
                url: '/accessLists',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'AccessLists'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/accessList/accessLists.html',
                        controller: 'AccessListController'
                    }
                },
                resolve: {
                }
            })
            .state('accessList.detail', {
                parent: 'entity',
                url: '/accessList/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'AccessList'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/accessList/accessList-detail.html',
                        controller: 'AccessListDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'AccessList', function($stateParams, AccessList) {
                        return AccessList.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('accessList.new', {
                parent: 'accessList',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/accessList/accessList-dialog.html',
                        controller: 'AccessListDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    userId: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('accessList', null, { reload: true });
                    }, function() {
                        $state.go('accessList');
                    })
                }]
            })*/
            /*.state('accessList.edit', {
                parent: 'accessList',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/accessList/accessList-dialog.html',
                        controller: 'AccessListDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['AccessList', function(AccessList) {
                                return AccessList.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('accessList', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('accessList.delete', {
                parent: 'accessList',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/accessList/accessList-delete-dialog.html',
                        controller: 'AccessListDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['AccessList', function(AccessList) {
                                return AccessList.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('accessList', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('accessList.new', {
                parent: 'accessList',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'AccessLists'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/accessList/accessList-dialog.html',
                         controller: 'AccessListDialogController'
                    }
                },
                resolve: {
                }
            })
             .state('accessList.edit', {
                parent: 'accessList',
                url: '/edit/:accessListId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'AccessLists'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/accessList/accessList-dialog.html',
                         controller: 'AccessListDialogController'
                    }
                },
                resolve: {
                }
            });
    });
