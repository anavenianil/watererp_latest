'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('mainSewerageSize', {
                parent: 'entity',
                url: '/mainSewerageSizes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MainSewerageSizes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mainSewerageSize/mainSewerageSizes.html',
                        controller: 'MainSewerageSizeController'
                    }
                },
                resolve: {
                }
            })
            .state('mainSewerageSize.detail', {
                parent: 'entity',
                url: '/mainSewerageSize/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MainSewerageSize'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mainSewerageSize/mainSewerageSize-detail.html',
                        controller: 'MainSewerageSizeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MainSewerageSize', function($stateParams, MainSewerageSize) {
                        return MainSewerageSize.get({id : $stateParams.id});
                    }]
                }
            })
            .state('mainSewerageSize.new', {
                parent: 'mainSewerageSize',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainSewerageSize/mainSewerageSize-dialog.html',
                        controller: 'MainSewerageSizeDialogController',
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
                        $state.go('mainSewerageSize', null, { reload: true });
                    }, function() {
                        $state.go('mainSewerageSize');
                    })
                }]
            })
            .state('mainSewerageSize.edit', {
                parent: 'mainSewerageSize',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainSewerageSize/mainSewerageSize-dialog.html',
                        controller: 'MainSewerageSizeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MainSewerageSize', function(MainSewerageSize) {
                                return MainSewerageSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('mainSewerageSize', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('mainSewerageSize.delete', {
                parent: 'mainSewerageSize',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/mainSewerageSize/mainSewerageSize-delete-dialog.html',
                        controller: 'MainSewerageSizeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MainSewerageSize', function(MainSewerageSize) {
                                return MainSewerageSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('mainSewerageSize', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
