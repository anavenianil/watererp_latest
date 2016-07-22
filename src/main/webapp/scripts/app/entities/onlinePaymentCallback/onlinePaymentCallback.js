'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('onlinePaymentCallback', {
                parent: 'entity',
                url: '/onlinePaymentCallbacks',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentCallbacks'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentCallback/onlinePaymentCallbacks.html',
                        controller: 'OnlinePaymentCallbackController'
                    }
                },
                resolve: {
                }
            })
            .state('onlinePaymentCallback.detail', {
                parent: 'entity',
                url: '/onlinePaymentCallback/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentCallback'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentCallback/onlinePaymentCallback-detail.html',
                        controller: 'OnlinePaymentCallbackDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OnlinePaymentCallback', function($stateParams, OnlinePaymentCallback) {
                        return OnlinePaymentCallback.get({id : $stateParams.id});
                    }]
                }
            })
            .state('onlinePaymentCallback.new', {
                parent: 'onlinePaymentCallback',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentCallback/onlinePaymentCallback-dialog.html',
                        controller: 'OnlinePaymentCallbackDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    currency: null,
                                    paymentMode: null,
                                    serviceCode: null,
                                    message: null,
                                    responseCode: null,
                                    totalAmountPaid: null,
                                    userDefinedField: null,
                                    merchantTxnRef: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentCallback', null, { reload: true });
                    }, function() {
                        $state.go('onlinePaymentCallback');
                    })
                }]
            })
            .state('onlinePaymentCallback.edit', {
                parent: 'onlinePaymentCallback',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentCallback/onlinePaymentCallback-dialog.html',
                        controller: 'OnlinePaymentCallbackDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OnlinePaymentCallback', function(OnlinePaymentCallback) {
                                return OnlinePaymentCallback.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentCallback', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('onlinePaymentCallback.delete', {
                parent: 'onlinePaymentCallback',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentCallback/onlinePaymentCallback-delete-dialog.html',
                        controller: 'OnlinePaymentCallbackDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OnlinePaymentCallback', function(OnlinePaymentCallback) {
                                return OnlinePaymentCallback.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentCallback', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
