'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('valveComplaint', {
                parent: 'entity',
                url: '/valveComplaints',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ValveComplaints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/valveComplaint/valveComplaints.html',
                        controller: 'ValveComplaintController'
                    }
                },
                resolve: {
                }
            })
            .state('valveComplaint.detail', {
                parent: 'entity',
                url: '/valveComplaint/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ValveComplaint'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/valveComplaint/valveComplaint-detail.html',
                        controller: 'ValveComplaintDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ValveComplaint', function($stateParams, ValveComplaint) {
                        return ValveComplaint.get({id : $stateParams.id});
                    }]
                }
            })
            .state('valveComplaint.new', {
                parent: 'valveComplaint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valveComplaint/valveComplaint-dialog.html',
                        controller: 'ValveComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    closedTime: null,
                                    openTime: null,
                                    colourCode: null,
                                    side: null,
                                    noOfTurns: null,
                                    valveSize: null,
                                    valveNo: null,
                                    repairCode: null,
                                    distanceLeft: null,
                                    distanceSb: null,
                                    distanceZ: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('valveComplaint', null, { reload: true });
                    }, function() {
                        $state.go('valveComplaint');
                    })
                }]
            })
            .state('valveComplaint.edit', {
                parent: 'valveComplaint',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valveComplaint/valveComplaint-dialog.html',
                        controller: 'ValveComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ValveComplaint', function(ValveComplaint) {
                                return ValveComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('valveComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('valveComplaint.delete', {
                parent: 'valveComplaint',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valveComplaint/valveComplaint-delete-dialog.html',
                        controller: 'ValveComplaintDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ValveComplaint', function(ValveComplaint) {
                                return ValveComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('valveComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
