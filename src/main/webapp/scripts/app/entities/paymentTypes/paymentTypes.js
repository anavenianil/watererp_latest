'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('paymentTypes', {
                parent: 'entity',
                url: '/paymentTypess',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PaymentTypess'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/paymentTypes/paymentTypess.html',
                        controller: 'PaymentTypesController'
                    }
                },
                resolve: {
                }
            })
            .state('paymentTypes.detail', {
                parent: 'entity',
                url: '/paymentTypes/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PaymentTypes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/paymentTypes/paymentTypes-detail.html',
                        controller: 'PaymentTypesDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PaymentTypes', function($stateParams, PaymentTypes) {
                        return PaymentTypes.get({id : $stateParams.id});
                    }]
                }
            })
            .state('paymentTypes.new', {
                parent: 'paymentTypes',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paymentTypes/paymentTypes-dialog.html',
                        controller: 'PaymentTypesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    paymentMode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('paymentTypes', null, { reload: true });
                    }, function() {
                        $state.go('paymentTypes');
                    })
                }]
            })
            .state('paymentTypes.edit', {
                parent: 'paymentTypes',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paymentTypes/paymentTypes-dialog.html',
                        controller: 'PaymentTypesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PaymentTypes', function(PaymentTypes) {
                                return PaymentTypes.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('paymentTypes', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('paymentTypes.delete', {
                parent: 'paymentTypes',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paymentTypes/paymentTypes-delete-dialog.html',
                        controller: 'PaymentTypesDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['PaymentTypes', function(PaymentTypes) {
                                return PaymentTypes.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('paymentTypes', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
