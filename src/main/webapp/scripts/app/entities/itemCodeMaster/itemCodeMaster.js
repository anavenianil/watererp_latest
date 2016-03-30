'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemCodeMaster', {
                parent: 'entity',
                url: '/itemCodeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCodeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMasters.html',
                        controller: 'ItemCodeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCodeMaster.detail', {
                parent: 'entity',
                url: '/itemCodeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCodeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-detail.html',
                        controller: 'ItemCodeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemCodeMaster', function($stateParams, ItemCodeMaster) {
                        return ItemCodeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('itemCodeMaster.new', {
                parent: 'itemCodeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-dialog.html',
                        controller: 'ItemCodeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    itemCode: null,
                                    itemName: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('itemCodeMaster');
                    })
                }]
            })*/
            /*.state('itemCodeMaster.edit', {
                parent: 'itemCodeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-dialog.html',
                        controller: 'ItemCodeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemCodeMaster', function(ItemCodeMaster) {
                                return ItemCodeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('itemCodeMaster.delete', {
                parent: 'itemCodeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-delete-dialog.html',
                        controller: 'ItemCodeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemCodeMaster', function(ItemCodeMaster) {
                                return ItemCodeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemCodeMaster.new', {
                parent: 'itemCodeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCodeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-dialog.html',
                        controller: 'ItemCodeMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCodeMaster.edit', {
                parent: 'itemCodeMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCodeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCodeMaster/itemCodeMaster-dialog.html',
                        controller: 'ItemCodeMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
