'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('adjustments', {
                parent: 'entity',
                url: '/adjustmentss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Adjustmentss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/adjustments/adjustmentss.html',
                        controller: 'AdjustmentsController'
                    }
                },
                resolve: {
                }
            })
            .state('adjustments.detail', {
                parent: 'entity',
                url: '/adjustments/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Adjustments'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/adjustments/adjustments-detail.html',
                        controller: 'AdjustmentsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Adjustments', function($stateParams, Adjustments) {
                        return Adjustments.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('adjustments.new', {
                parent: 'adjustments',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/adjustments/adjustments-dialog.html',
                        controller: 'AdjustmentsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    amount: null,
                                    remarks: null,
                                    txnTime: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('adjustments', null, { reload: true });
                    }, function() {
                        $state.go('adjustments');
                    })
                }]
            })*/
            /*.state('adjustments.edit', {
                parent: 'adjustments',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/adjustments/adjustments-dialog.html',
                        controller: 'AdjustmentsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Adjustments', function(Adjustments) {
                                return Adjustments.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('adjustments', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('adjustments.delete', {
                parent: 'adjustments',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/adjustments/adjustments-delete-dialog.html',
                        controller: 'AdjustmentsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Adjustments', function(Adjustments) {
                                return Adjustments.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('adjustments', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('adjustments.new', {
                parent: 'adjustments',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Adjustmentss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/adjustments/adjustments-dialog.html',
                        controller: 'AdjustmentsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('adjustments.edit', {
                parent: 'adjustments',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Adjustmentss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/adjustments/adjustments-dialog.html',
                        controller: 'AdjustmentsDialogController'
                    }
                },
                resolve: {
                }
            });
    });
