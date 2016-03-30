'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemSubCodeMaster', {
                parent: 'entity',
                url: '/itemSubCodeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCodeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMasters.html',
                        controller: 'ItemSubCodeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('itemSubCodeMaster.detail', {
                parent: 'entity',
                url: '/itemSubCodeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCodeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-detail.html',
                        controller: 'ItemSubCodeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemSubCodeMaster', function($stateParams, ItemSubCodeMaster) {
                        return ItemSubCodeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('itemSubCodeMaster.new', {
                parent: 'itemSubCodeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-dialog.html',
                        controller: 'ItemSubCodeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    itemCodeId: null,
                                    itemSubCode: null,
                                    itemName: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    itemCcodeId: null,
                                    itemCategoryId: null,
                                    itemSubCategoryID: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('itemSubCodeMaster');
                    })
                }]
            })*/
            /*.state('itemSubCodeMaster.edit', {
                parent: 'itemSubCodeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-dialog.html',
                        controller: 'ItemSubCodeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemSubCodeMaster', function(ItemSubCodeMaster) {
                                return ItemSubCodeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('itemSubCodeMaster.delete', {
                parent: 'itemSubCodeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-delete-dialog.html',
                        controller: 'ItemSubCodeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemSubCodeMaster', function(ItemSubCodeMaster) {
                                return ItemSubCodeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemSubCodeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemSubCodeMaster.new', {
                parent: 'itemSubCodeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCodeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-dialog.html',
                        controller: 'ItemSubCodeMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('itemSubCodeMaster.edit', {
                parent: 'itemSubCodeMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemSubCodeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemSubCodeMaster/itemSubCodeMaster-dialog.html',
                        controller: 'ItemSubCodeMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
