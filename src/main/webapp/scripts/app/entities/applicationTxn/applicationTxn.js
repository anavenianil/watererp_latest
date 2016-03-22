'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('applicationTxn', {
                parent: 'entity',
                url: '/applicationTxns',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxns.html',
                        controller: 'ApplicationTxnController'
                    }
                },
                resolve: {
                }
            })
            .state('applicationTxn.detail', {
                parent: 'entity',
                url: '/applicationTxn/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxn'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-detail.html',
                        controller: 'ApplicationTxnDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ApplicationTxn', function($stateParams, ApplicationTxn) {
                        return ApplicationTxn.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('applicationTxn.new', {
                parent: 'applicationTxn',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    sHouseNo: null,
                                    govtOfficialNo: null,
                                    ward: null,
                                    street: null,
                                    pincode: null,
                                    block: null,
                                    area: null,
                                    section: null,
                                    constituency: null,
                                    email: null,
                                    telephoneNumber: null,
                                    mobile: null,
                                    scanPlan: null,
                                    scanPlan1: null,
                                    saleDeed: null,
                                    saleDeed1: null,
                                    totalPlinthArea: null,
                                    createdDate: null,
                                    updatedDate: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('applicationTxn');
                    })
                }]
            })*/
            /*.state('applicationTxn.edit', {
                parent: 'applicationTxn',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ApplicationTxn', function(ApplicationTxn) {
                                return ApplicationTxn.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('applicationTxn.delete', {
                parent: 'applicationTxn',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-delete-dialog.html',
                        controller: 'ApplicationTxnDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ApplicationTxn', function(ApplicationTxn) {
                                return ApplicationTxn.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('applicationTxn.new', {
                parent: 'applicationTxn',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                         controller: 'ApplicationTxnDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('applicationTxn.edit', {
                parent: 'applicationTxn',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                         controller: 'ApplicationTxnDialogController'
                    }
                },
                resolve: {
                	entity: ['$stateParams', 'ApplicationTxn', function($stateParams, ApplicationTxn) {
                        return ApplicationTxn.get({id : $stateParams.id});
                    }]
                }
            });
    });
