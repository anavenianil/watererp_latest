'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('meterDetails', {
                parent: 'entity',
                url: '/meterDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MeterDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterDetails/meterDetailss.html',
                        controller: 'MeterDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('meterDetails.detail', {
                parent: 'entity',
                url: '/meterDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MeterDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterDetails/meterDetails-detail.html',
                        controller: 'MeterDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MeterDetails', function($stateParams, MeterDetails) {
                        return MeterDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('meterDetails.new', {
                parent: 'meterDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterDetails/meterDetails-dialog.html',
                        controller: 'MeterDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    meterId: null,
                                    meterType: null,
                                    meterMake: null,
                                    min: null,
                                    max: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('meterDetails', null, { reload: true });
                    }, function() {
                        $state.go('meterDetails');
                    })
                }]
            })
            .state('meterDetails.edit', {
                parent: 'meterDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterDetails/meterDetails-dialog.html',
                        controller: 'MeterDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MeterDetails', function(MeterDetails) {
                                return MeterDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('meterDetails.delete', {
                parent: 'meterDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterDetails/meterDetails-delete-dialog.html',
                        controller: 'MeterDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MeterDetails', function(MeterDetails) {
                                return MeterDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
