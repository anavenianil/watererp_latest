'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('onlinePaymentResponse', {
                parent: 'entity',
                url: '/onlinePaymentResponses',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentResponses'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentResponse/onlinePaymentResponses.html',
                        controller: 'OnlinePaymentResponseController'
                    }
                },
                resolve: {
                }
            })
            .state('onlinePaymentResponse.detail', {
                parent: 'entity',
                url: '/onlinePaymentResponse/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'OnlinePaymentResponse'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/onlinePaymentResponse/onlinePaymentResponse-detail.html',
                        controller: 'OnlinePaymentResponseDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'OnlinePaymentResponse', function($stateParams, OnlinePaymentResponse) {
                        return OnlinePaymentResponse.get({id : $stateParams.id});
                    }]
                }
            })
            .state('onlinePaymentResponse.new', {
                parent: 'onlinePaymentResponse',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentResponse/onlinePaymentResponse-dialog.html',
                        controller: 'OnlinePaymentResponseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    responseCode: null,
                                    responseTime: null,
                                    redirectUrl: null,
                                    merchantTxnRef: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentResponse', null, { reload: true });
                    }, function() {
                        $state.go('onlinePaymentResponse');
                    })
                }]
            })
            .state('onlinePaymentResponse.edit', {
                parent: 'onlinePaymentResponse',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentResponse/onlinePaymentResponse-dialog.html',
                        controller: 'OnlinePaymentResponseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['OnlinePaymentResponse', function(OnlinePaymentResponse) {
                                return OnlinePaymentResponse.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentResponse', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('onlinePaymentResponse.delete', {
                parent: 'onlinePaymentResponse',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/onlinePaymentResponse/onlinePaymentResponse-delete-dialog.html',
                        controller: 'OnlinePaymentResponseDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['OnlinePaymentResponse', function(OnlinePaymentResponse) {
                                return OnlinePaymentResponse.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('onlinePaymentResponse', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
