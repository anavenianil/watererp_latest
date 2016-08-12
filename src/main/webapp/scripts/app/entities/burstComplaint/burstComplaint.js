'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('burstComplaint', {
                parent: 'entity',
                url: '/burstComplaints',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BurstComplaints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/burstComplaint/burstComplaints.html',
                        controller: 'BurstComplaintController'
                    }
                },
                resolve: {
                }
            })
            .state('burstComplaint.detail', {
                parent: 'entity',
                url: '/burstComplaint/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BurstComplaint'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/burstComplaint/burstComplaint-detail.html',
                        controller: 'BurstComplaintDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BurstComplaint', function($stateParams, BurstComplaint) {
                        return BurstComplaint.get({id : $stateParams.id});
                    }]
                }
            })
            .state('burstComplaint.new', {
                parent: 'burstComplaint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/burstComplaint/burstComplaint-dialog.html',
                        controller: 'BurstComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    meterLocation: null,
                                    burstPipe: null,
                                    fromLeft: null,
                                    fromSb: null,
                                    burstArea: null,
                                    pipeType: null,
                                    pipeSize: null,
                                    holeSize: null,
                                    repairCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('burstComplaint', null, { reload: true });
                    }, function() {
                        $state.go('burstComplaint');
                    })
                }]
            })
            .state('burstComplaint.edit', {
                parent: 'burstComplaint',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/burstComplaint/burstComplaint-dialog.html',
                        controller: 'BurstComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BurstComplaint', function(BurstComplaint) {
                                return BurstComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('burstComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('burstComplaint.delete', {
                parent: 'burstComplaint',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/burstComplaint/burstComplaint-delete-dialog.html',
                        controller: 'BurstComplaintDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BurstComplaint', function(BurstComplaint) {
                                return BurstComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('burstComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
