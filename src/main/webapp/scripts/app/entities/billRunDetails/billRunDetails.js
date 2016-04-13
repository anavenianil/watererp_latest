'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billRunDetails', {
                parent: 'entity',
                url: '/billRunDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillRunDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetailss.html',
                        controller: 'BillRunDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billRunDetailsByRunId', {
                parent: 'entity',
                url: '/billRunDetailss/billRun/{billRunId}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillRunDetails by Bill Run'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetailss.html',
                        controller: 'BillRunDetailsController'
                    }
                },
                resolve: {
//                    entity: ['$stateParams', 'BillRunDetailsByRunId', function($stateParams, BillRunDetailsByRunId) {
//                    	console.log("Entered here." + JSON.stringify(BillRunDetailsByRunId))
//                        return BillRunDetailsByRunId.get({id : $stateParams.billRunId});
//                    }]
                }
            })
            .state('billRunDetails.detail', {
                parent: 'entity',
                url: '/billRunDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillRunDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetails-detail.html',
                        controller: 'BillRunDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillRunDetails', function($stateParams, BillRunDetails) {
                        return BillRunDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billRunDetails.new', {
                parent: 'billRunDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetails-dialog.html',
                        controller: 'BillRunDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    fromDt: null,
                                    toDt: null,
                                    status: null,
                                    remarks: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billRunDetails', null, { reload: true });
                    }, function() {
                        $state.go('billRunDetails');
                    })
                }]
            })
            .state('billRunDetails.edit', {
                parent: 'billRunDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetails-dialog.html',
                        controller: 'BillRunDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillRunDetails', function(BillRunDetails) {
                                return BillRunDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billRunDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('billRunDetails.delete', {
                parent: 'billRunDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billRunDetails/billRunDetails-delete-dialog.html',
                        controller: 'BillRunDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillRunDetails', function(BillRunDetails) {
                                return BillRunDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billRunDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
