'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemRequired', {
                parent: 'entity',
                url: '/itemRequireds',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemRequireds'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequireds.html',
                        controller: 'ItemRequiredController'
                    }
                },
                resolve: {
                }
            })
            .state('itemRequired.detail', {
                parent: 'entity',
                url: '/itemRequired/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemRequired'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequired-detail.html',
                        controller: 'ItemRequiredDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemRequired', function($stateParams, ItemRequired) {
                        return ItemRequired.get({id : $stateParams.id});
                    }]
                }
            })
            .state('itemRequired.new', {
                parent: 'itemRequired',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequired-dialog.html',
                        controller: 'ItemRequiredDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    description: null,
                                    unit: null,
                                    quantity: null,
                                    ratePerShs: null,
                                    amount: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemRequired', null, { reload: true });
                    }, function() {
                        $state.go('itemRequired');
                    })
                }]
            })
            .state('itemRequired.edit', {
                parent: 'itemRequired',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequired-dialog.html',
                        controller: 'ItemRequiredDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemRequired', function(ItemRequired) {
                                return ItemRequired.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemRequired', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemRequired.delete', {
                parent: 'itemRequired',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequired-delete-dialog.html',
                        controller: 'ItemRequiredDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemRequired', function(ItemRequired) {
                                return ItemRequired.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemRequired', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemRequiredForApplicationTxn', {
                parent: 'entity',
                url: '/itemRequireds/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemRequireds'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemRequired/itemRequireds.html',
                        controller: 'ItemRequiredController'
                    }
                },
                resolve: {
                }
            });
    });
