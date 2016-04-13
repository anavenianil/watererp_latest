'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('meterStatus', {
                parent: 'entity',
                url: '/meterStatuss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MeterStatuss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterStatus/meterStatuss.html',
                        controller: 'MeterStatusController'
                    }
                },
                resolve: {
                }
            })
            .state('meterStatus.detail', {
                parent: 'entity',
                url: '/meterStatus/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MeterStatus'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterStatus/meterStatus-detail.html',
                        controller: 'MeterStatusDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MeterStatus', function($stateParams, MeterStatus) {
                        return MeterStatus.get({id : $stateParams.id});
                    }]
                }
            })
            .state('meterStatus.new', {
                parent: 'meterStatus',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterStatus/meterStatus-dialog.html',
                        controller: 'MeterStatusDialogController',
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
                        $state.go('meterStatus', null, { reload: true });
                    }, function() {
                        $state.go('meterStatus');
                    })
                }]
            })
            .state('meterStatus.edit', {
                parent: 'meterStatus',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterStatus/meterStatus-dialog.html',
                        controller: 'MeterStatusDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MeterStatus', function(MeterStatus) {
                                return MeterStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('meterStatus.delete', {
                parent: 'meterStatus',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterStatus/meterStatus-delete-dialog.html',
                        controller: 'MeterStatusDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MeterStatus', function(MeterStatus) {
                                return MeterStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
