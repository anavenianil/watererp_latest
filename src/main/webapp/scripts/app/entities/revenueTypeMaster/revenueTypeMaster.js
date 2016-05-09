'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('revenueTypeMaster', {
                parent: 'entity',
                url: '/revenueTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RevenueTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/revenueTypeMaster/revenueTypeMasters.html',
                        controller: 'RevenueTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('revenueTypeMaster.detail', {
                parent: 'entity',
                url: '/revenueTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RevenueTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/revenueTypeMaster/revenueTypeMaster-detail.html',
                        controller: 'RevenueTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RevenueTypeMaster', function($stateParams, RevenueTypeMaster) {
                        return RevenueTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('revenueTypeMaster.new', {
                parent: 'revenueTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/revenueTypeMaster/revenueTypeMaster-dialog.html',
                        controller: 'RevenueTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    revenueType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('revenueTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('revenueTypeMaster');
                    })
                }]
            })
            .state('revenueTypeMaster.edit', {
                parent: 'revenueTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/revenueTypeMaster/revenueTypeMaster-dialog.html',
                        controller: 'RevenueTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RevenueTypeMaster', function(RevenueTypeMaster) {
                                return RevenueTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('revenueTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('revenueTypeMaster.delete', {
                parent: 'revenueTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/revenueTypeMaster/revenueTypeMaster-delete-dialog.html',
                        controller: 'RevenueTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RevenueTypeMaster', function(RevenueTypeMaster) {
                                return RevenueTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('revenueTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
