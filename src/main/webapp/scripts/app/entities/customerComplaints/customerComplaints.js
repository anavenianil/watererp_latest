'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('customerComplaints', {
                parent: 'entity',
                url: '/customerComplaintss',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'CustomerComplaintss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/customerComplaints/customerComplaintss.html',
                        controller: 'CustomerComplaintsController'
                    }
                },
                resolve: {
                }
            })
            .state('customerComplaints.detail', {
                parent: 'entity',
                url: '/customerComplaints/{id}',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'CustomerComplaints'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-detail.html',
                        controller: 'CustomerComplaintsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CustomerComplaints', function($stateParams, CustomerComplaints) {
                        return CustomerComplaints.get({id : $stateParams.id});
                    }]
                }
            })
            .state('customerComplaints.new', {
                parent: 'customerComplaints',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'CustomerComplaintss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-dialog.html',
                        controller: 'CustomerComplaintsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('customerComplaints.edit', {
                parent: 'customerComplaints',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustomerComplaintss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-dialog.html',
                        controller: 'CustomerComplaintsDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('customerComplaints.new', {
                parent: 'customerComplaints',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-dialog.html',
                        controller: 'CustomerComplaintsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    remarks: null,
                                    relevantDoc: null,
                                    complaintBy: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('customerComplaints', null, { reload: true });
                    }, function() {
                        $state.go('customerComplaints');
                    })
                }]
            })
            .state('customerComplaints.edit', {
                parent: 'customerComplaints',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-dialog.html',
                        controller: 'CustomerComplaintsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CustomerComplaints', function(CustomerComplaints) {
                                return CustomerComplaints.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('customerComplaints', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('customerComplaints.delete', {
                parent: 'customerComplaints',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customerComplaints/customerComplaints-delete-dialog.html',
                        controller: 'CustomerComplaintsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CustomerComplaints', function(CustomerComplaints) {
                                return CustomerComplaints.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('customerComplaints', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
