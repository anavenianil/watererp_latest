'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('onlinePaymentOrder', {
                parent: 'entity',
                url: '/onlinePaymentOrders',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentOrders'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentOrder/onlinePaymentOrders.html',
                        controller: 'OnlinePaymentOrderController'
                    }
                },
                resolve: {
                }
            })
            .state('onlinePaymentOrder.detail', {
                parent: 'entity',
                url: '/onlinePaymentOrder/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentOrder'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentOrder/onlinePaymentOrder-detail.html',
                        controller: 'OnlinePaymentOrderDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OnlinePaymentOrder', function($stateParams, OnlinePaymentOrder) {
                        return OnlinePaymentOrder.get({id : $stateParams.id});
                    }]
                }
            })
            .state('onlinePaymentOrder.new', {
                parent: 'onlinePaymentOrder',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentOrder/onlinePaymentOrder-dialog.html',
                        controller: 'OnlinePaymentOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    serviceCode: null,
                                    amount: null,
                                    payBy: null,
                                    userDefinedField: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentOrder', null, { reload: true });
                    }, function() {
                        $state.go('onlinePaymentOrder');
                    })
                }]
            })
            .state('onlinePaymentOrder.edit', {
                parent: 'onlinePaymentOrder',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentOrder/onlinePaymentOrder-dialog.html',
                        controller: 'OnlinePaymentOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OnlinePaymentOrder', function(OnlinePaymentOrder) {
                                return OnlinePaymentOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('onlinePaymentOrder.delete', {
                parent: 'onlinePaymentOrder',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentOrder/onlinePaymentOrder-delete-dialog.html',
                        controller: 'OnlinePaymentOrderDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OnlinePaymentOrder', function(OnlinePaymentOrder) {
                                return OnlinePaymentOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
