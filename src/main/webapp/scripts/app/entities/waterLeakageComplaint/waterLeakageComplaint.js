'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('waterLeakageComplaint', {
                parent: 'entity',
                url: '/waterLeakageComplaints',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterLeakageComplaints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaints.html',
                        controller: 'WaterLeakageComplaintController'
                    }
                },
                resolve: {
                }
            })
            .state('waterLeakageComplaint.detail', {
                parent: 'entity',
                url: '/waterLeakageComplaint/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterLeakageComplaint'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-detail.html',
                        controller: 'WaterLeakageComplaintDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WaterLeakageComplaint', function($stateParams, WaterLeakageComplaint) {
                        return WaterLeakageComplaint.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('waterLeakageComplaint.new', {
                parent: 'waterLeakageComplaint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-dialog.html',
                        controller: 'WaterLeakageComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    leakageType: null,
                                    coordinateX: null,
                                    coordinateY: null,
                                    leakMagnitude: null,
                                    complaintDateTime: null,
                                    daysRequired: null,
                                    staffRequired: null,
                                    complaintBy: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('waterLeakageComplaint', null, { reload: true });
                    }, function() {
                        $state.go('waterLeakageComplaint');
                    })
                }]
            })*/
            /*.state('waterLeakageComplaint.edit', {
                parent: 'waterLeakageComplaint',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-dialog.html',
                        controller: 'WaterLeakageComplaintDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WaterLeakageComplaint', function(WaterLeakageComplaint) {
                                return WaterLeakageComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('waterLeakageComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('waterLeakageComplaint.delete', {
                parent: 'waterLeakageComplaint',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-delete-dialog.html',
                        controller: 'WaterLeakageComplaintDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WaterLeakageComplaint', function(WaterLeakageComplaint) {
                                return WaterLeakageComplaint.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('waterLeakageComplaint', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('waterLeakageComplaint.new', {
                parent: 'waterLeakageComplaint',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterLeakageComplaints'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-dialog.html',
                         controller: 'WaterLeakageComplaintDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('waterLeakageComplaint.edit', {
                parent: 'waterLeakageComplaint',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'WaterLeakageComplaint'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/waterLeakageComplaint/waterLeakageComplaint-dialog.html',
                        controller: 'WaterLeakageComplaintDialogController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'WaterLeakageComplaint', function($stateParams, WaterLeakageComplaint) {
                        return WaterLeakageComplaint.get({id : $stateParams.id});
                    }]
                }
            });
    });
