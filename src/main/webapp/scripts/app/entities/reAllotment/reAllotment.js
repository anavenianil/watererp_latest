'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reAllotment', {
                parent: 'entity',
                url: '/reAllotments',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReAllotments'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reAllotment/reAllotments.html',
                        controller: 'ReAllotmentController'
                    }
                },
                resolve: {
                }
            })
            .state('reAllotment.detail', {
                parent: 'entity',
                url: '/reAllotment/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReAllotment'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reAllotment/reAllotment-detail.html',
                        controller: 'ReAllotmentDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ReAllotment', function($stateParams, ReAllotment) {
                        return ReAllotment.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reAllotment.new', {
                parent: 'reAllotment',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reAllotment/reAllotment-dialog.html',
                        controller: 'ReAllotmentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('reAllotment', null, { reload: true });
                    }, function() {
                        $state.go('reAllotment');
                    })
                }]
            })
            .state('reAllotment.edit', {
                parent: 'reAllotment',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reAllotment/reAllotment-dialog.html',
                        controller: 'ReAllotmentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ReAllotment', function(ReAllotment) {
                                return ReAllotment.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reAllotment', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('reAllotment.delete', {
                parent: 'reAllotment',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reAllotment/reAllotment-delete-dialog.html',
                        controller: 'ReAllotmentDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ReAllotment', function(ReAllotment) {
                                return ReAllotment.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reAllotment', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
