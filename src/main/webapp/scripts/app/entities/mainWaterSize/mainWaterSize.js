'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('mainWaterSize', {
                parent: 'entity',
                url: '/mainWaterSizes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MainWaterSizes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mainWaterSize/mainWaterSizes.html',
                        controller: 'MainWaterSizeController'
                    }
                },
                resolve: {
                }
            })
            .state('mainWaterSize.detail', {
                parent: 'entity',
                url: '/mainWaterSize/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MainWaterSize'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mainWaterSize/mainWaterSize-detail.html',
                        controller: 'MainWaterSizeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MainWaterSize', function($stateParams, MainWaterSize) {
                        return MainWaterSize.get({id : $stateParams.id});
                    }]
                }
            })
            .state('mainWaterSize.new', {
                parent: 'mainWaterSize',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainWaterSize/mainWaterSize-dialog.html',
                        controller: 'MainWaterSizeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    size: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('mainWaterSize', null, { reload: true });
                    }, function() {
                        $state.go('mainWaterSize');
                    })
                }]
            })
            .state('mainWaterSize.edit', {
                parent: 'mainWaterSize',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainWaterSize/mainWaterSize-dialog.html',
                        controller: 'MainWaterSizeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MainWaterSize', function(MainWaterSize) {
                                return MainWaterSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('mainWaterSize', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('mainWaterSize.delete', {
                parent: 'mainWaterSize',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainWaterSize/mainWaterSize-delete-dialog.html',
                        controller: 'MainWaterSizeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MainWaterSize', function(MainWaterSize) {
                                return MainWaterSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('mainWaterSize', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
