'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jobCardSiteStatus', {
                parent: 'entity',
                url: '/jobCardSiteStatuss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobCardSiteStatuss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobCardSiteStatus/jobCardSiteStatuss.html',
                        controller: 'JobCardSiteStatusController'
                    }
                },
                resolve: {
                }
            })
            .state('jobCardSiteStatus.detail', {
                parent: 'entity',
                url: '/jobCardSiteStatus/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobCardSiteStatus'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobCardSiteStatus/jobCardSiteStatus-detail.html',
                        controller: 'JobCardSiteStatusDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'JobCardSiteStatus', function($stateParams, JobCardSiteStatus) {
                        return JobCardSiteStatus.get({id : $stateParams.id});
                    }]
                }
            })
            .state('jobCardSiteStatus.new', {
                parent: 'jobCardSiteStatus',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardSiteStatus/jobCardSiteStatus-dialog.html',
                        controller: 'JobCardSiteStatusDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    tarPatching: null,
                                    tarPatchingLength: null,
                                    tarPatchingBreadth: null,
                                    cleanSite: null,
                                    backFill: null,
                                    backFillLength: null,
                                    backFillBreadth: null,
                                    brickLayer: null,
                                    paving: null,
                                    pavingLength: null,
                                    pavingBreadth: null,
                                    unableToLocate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardSiteStatus', null, { reload: true });
                    }, function() {
                        $state.go('jobCardSiteStatus');
                    })
                }]
            })
            .state('jobCardSiteStatus.edit', {
                parent: 'jobCardSiteStatus',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardSiteStatus/jobCardSiteStatus-dialog.html',
                        controller: 'JobCardSiteStatusDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['JobCardSiteStatus', function(JobCardSiteStatus) {
                                return JobCardSiteStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardSiteStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('jobCardSiteStatus.delete', {
                parent: 'jobCardSiteStatus',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardSiteStatus/jobCardSiteStatus-delete-dialog.html',
                        controller: 'JobCardSiteStatusDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['JobCardSiteStatus', function(JobCardSiteStatus) {
                                return JobCardSiteStatus.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardSiteStatus', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
