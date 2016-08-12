'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('hydrantComplaint', {
                parent: 'entity',
                url: '/hydrantComplaints',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'HydrantComplaints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hydrantComplaint/hydrantComplaints.html',
                        controller: 'HydrantComplaintController'
                    }
                },
                resolve: {
                }
            })
            .state('hydrantComplaint.detail', {
                parent: 'entity',
                url: '/hydrantComplaint/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'HydrantComplaint'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hydrantComplaint/hydrantComplaint-detail.html',
                        controller: 'HydrantComplaintDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'HydrantComplaint', function($stateParams, HydrantComplaint) {
                        return HydrantComplaint.get({id : $stateParams.id});
                    }]
                }
            })
            .state('hydrantComplaint.new', {
                parent: 'hydrantComplaint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/hydrantComplaint/hydrantComplaint-dialog.html',
                        controller: 'HydrantComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    fromLeft: null,
                                    fromSb: null,
                                    problemAt: null,
                                    activityType: null,
                                    pressure: null,
                                    pressureTime: null,
                                    flow: null,
                                    flowTime: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('hydrantComplaint', null, { reload: true });
                    }, function() {
                        $state.go('hydrantComplaint');
                    })
                }]
            })
            .state('hydrantComplaint.edit', {
                parent: 'hydrantComplaint',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/hydrantComplaint/hydrantComplaint-dialog.html',
                        controller: 'HydrantComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['HydrantComplaint', function(HydrantComplaint) {
                                return HydrantComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('hydrantComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('hydrantComplaint.delete', {
                parent: 'hydrantComplaint',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/hydrantComplaint/hydrantComplaint-delete-dialog.html',
                        controller: 'HydrantComplaintDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['HydrantComplaint', function(HydrantComplaint) {
                                return HydrantComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('hydrantComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
