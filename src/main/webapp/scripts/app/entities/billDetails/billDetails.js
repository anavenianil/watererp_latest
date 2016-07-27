'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billDetails', {
                parent: 'entity',
                url: '/billDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetailss.html',
                        controller: 'BillDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.detail', {
                parent: 'entity',
                url: '/billDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-detail.html',
                        controller: 'BillDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillDetails', function($stateParams, BillDetails) {
                        return BillDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billDetails.new', {
                parent: 'billDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.edit', {
                parent: 'billDetails',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.delete', {
                parent: 'billDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-delete-dialog.html',
                        controller: 'BillDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillDetails', function(BillDetails) {
                                return BillDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
