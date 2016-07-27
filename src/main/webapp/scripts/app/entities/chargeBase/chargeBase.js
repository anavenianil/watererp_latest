'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('chargeBase', {
                parent: 'entity',
                url: '/chargeBases',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ChargeBases'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/chargeBase/chargeBases.html',
                        controller: 'ChargeBaseController'
                    }
                },
                resolve: {
                }
            })
            .state('chargeBase.detail', {
                parent: 'entity',
                url: '/chargeBase/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ChargeBase'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/chargeBase/chargeBase-detail.html',
                        controller: 'ChargeBaseDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ChargeBase', function($stateParams, ChargeBase) {
                        return ChargeBase.get({id : $stateParams.id});
                    }]
                }
            })
            .state('chargeBase.new', {
                parent: 'chargeBase',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chargeBase/chargeBase-dialog.html',
                        controller: 'ChargeBaseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    code: null,
                                    name: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('chargeBase', null, { reload: true });
                    }, function() {
                        $state.go('chargeBase');
                    })
                }]
            })
            .state('chargeBase.edit', {
                parent: 'chargeBase',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chargeBase/chargeBase-dialog.html',
                        controller: 'ChargeBaseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ChargeBase', function(ChargeBase) {
                                return ChargeBase.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('chargeBase', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('chargeBase.delete', {
                parent: 'chargeBase',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chargeBase/chargeBase-delete-dialog.html',
                        controller: 'ChargeBaseDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ChargeBase', function(ChargeBase) {
                                return ChargeBase.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('chargeBase', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
