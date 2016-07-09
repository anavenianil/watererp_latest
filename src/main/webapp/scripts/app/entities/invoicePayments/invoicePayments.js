'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('invoicePayments', {
                parent: 'entity',
                url: '/invoicePaymentss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'InvoicePaymentss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/invoicePayments/invoicePaymentss.html',
                        controller: 'InvoicePaymentsController'
                    }
                },
                resolve: {
                }
            })
            .state('invoicePayments.detail', {
                parent: 'entity',
                url: '/invoicePayments/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'InvoicePayments'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/invoicePayments/invoicePayments-detail.html',
                        controller: 'InvoicePaymentsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'InvoicePayments', function($stateParams, InvoicePayments) {
                        return InvoicePayments.get({id : $stateParams.id});
                    }]
                }
            })
            .state('invoicePayments.new', {
                parent: 'invoicePayments',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/invoicePayments/invoicePayments-dialog.html',
                        controller: 'InvoicePaymentsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('invoicePayments', null, { reload: true });
                    }, function() {
                        $state.go('invoicePayments');
                    })
                }]
            })
            .state('invoicePayments.edit', {
                parent: 'invoicePayments',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/invoicePayments/invoicePayments-dialog.html',
                        controller: 'InvoicePaymentsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['InvoicePayments', function(InvoicePayments) {
                                return InvoicePayments.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('invoicePayments', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('invoicePayments.delete', {
                parent: 'invoicePayments',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/invoicePayments/invoicePayments-delete-dialog.html',
                        controller: 'InvoicePaymentsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['InvoicePayments', function(InvoicePayments) {
                                return InvoicePayments.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('invoicePayments', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
