'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('sewerageRequest', {
                parent: 'entity',
                url: '/sewerageRequests',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                    pageTitle: 'SewerageRequests'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequests.html',
                        controller: 'SewerageRequestController'
                    }
                },
                resolve: {
                }
            })
            .state('sewerageRequest.detail', {
                parent: 'entity',
                url: '/sewerageRequest/{id}/{requestTypeId}',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                    pageTitle: 'SewerageRequest'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-detail.html',
                        controller: 'SewerageRequestDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SewerageRequest', function($stateParams, SewerageRequest) {
                        return SewerageRequest.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('sewerageRequest.new', {
                parent: 'sewerageRequest',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-dialog.html',
                        controller: 'SewerageRequestDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    customerName: null,
                                    mobileNo: null,
                                    requestedDate: null,
                                    receiptNo: null,
                                    address: null,
                                    amount: null,
                                    paymentDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageRequest', null, { reload: true });
                    }, function() {
                        $state.go('sewerageRequest');
                    })
                }]
            })*/
            /*.state('sewerageRequest.edit', {
                parent: 'sewerageRequest',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-dialog.html',
                        controller: 'SewerageRequestDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['SewerageRequest', function(SewerageRequest) {
                                return SewerageRequest.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageRequest', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('sewerageRequest.delete', {
                parent: 'sewerageRequest',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-delete-dialog.html',
                        controller: 'SewerageRequestDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SewerageRequest', function(SewerageRequest) {
                                return SewerageRequest.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageRequest', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('sewerageRequest.new', {
                parent: 'sewerageRequest',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                    pageTitle: 'SewerageRequests'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-dialog.html',
                        controller: 'SewerageRequestDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('sewerageRequest.edit', {
                parent: 'sewerageRequest',
                url: '/edit/:{id}',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER', 'ROLE_ADMIN'],
                    pageTitle: 'SewerageRequests'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/sewerageRequest/sewerageRequest-dialog.html',
                        controller: 'SewerageRequestDialogController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SewerageRequest', function($stateParams, SewerageRequest) {
                        return SewerageRequest.get({id : $stateParams.id});
                    }]
                }
            });
    });
