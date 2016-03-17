'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemDetails', {
                parent: 'entity',
                url: '/itemDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemDetails/itemDetailss.html',
                        controller: 'ItemDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('itemDetails.detail', {
                parent: 'entity',
                url: '/itemDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemDetails/itemDetails-detail.html',
                        controller: 'ItemDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemDetails', function($stateParams, ItemDetails) {
                        return ItemDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('itemDetails.new', {
                parent: 'itemDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemDetails/itemDetails-dialog.html',
                        controller: 'ItemDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    itemCode: null,
                                    itemName: null,
                                    itemDescription: null,
                                    size: null,
                                    itemQuantity: null,
                                    unitPrice: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemDetails', null, { reload: true });
                    }, function() {
                        $state.go('itemDetails');
                    })
                }]
            })
            .state('itemDetails.edit', {
                parent: 'itemDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemDetails/itemDetails-dialog.html',
                        controller: 'ItemDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemDetails', function(ItemDetails) {
                                return ItemDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemDetails.delete', {
                parent: 'itemDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemDetails/itemDetails-delete-dialog.html',
                        controller: 'ItemDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemDetails', function(ItemDetails) {
                                return ItemDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
