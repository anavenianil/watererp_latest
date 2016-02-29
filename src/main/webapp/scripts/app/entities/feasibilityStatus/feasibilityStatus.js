'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('feasibilityStatus', {
                parent: 'entity',
                url: '/feasibilityStatuss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStatuss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStatus/feasibilityStatuss.html',
                        controller: 'FeasibilityStatusController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStatus.detail', {
                parent: 'entity',
                url: '/feasibilityStatus/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStatus'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStatus/feasibilityStatus-detail.html',
                        controller: 'FeasibilityStatusDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'FeasibilityStatus', function($stateParams, FeasibilityStatus) {
                        return FeasibilityStatus.get({id : $stateParams.id});
                    }]
                }
            })
            .state('feasibilityStatus.new', {
                parent: 'feasibilityStatus',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStatus/feasibilityStatus-dialog.html',
                        controller: 'FeasibilityStatusDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStatus', null, { reload: true });
                    }, function() {
                        $state.go('feasibilityStatus');
                    })
                }]
            })
            .state('feasibilityStatus.edit', {
                parent: 'feasibilityStatus',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStatus/feasibilityStatus-dialog.html',
                        controller: 'FeasibilityStatusDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['FeasibilityStatus', function(FeasibilityStatus) {
                                return FeasibilityStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('feasibilityStatus.delete', {
                parent: 'feasibilityStatus',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStatus/feasibilityStatus-delete-dialog.html',
                        controller: 'FeasibilityStatusDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['FeasibilityStatus', function(FeasibilityStatus) {
                                return FeasibilityStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
