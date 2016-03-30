'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('itemCompanyMaster', {
                parent: 'entity',
                url: '/itemCompanyMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCompanyMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMasters.html',
                        controller: 'ItemCompanyMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCompanyMaster.detail', {
                parent: 'entity',
                url: '/itemCompanyMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCompanyMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-detail.html',
                        controller: 'ItemCompanyMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ItemCompanyMaster', function($stateParams, ItemCompanyMaster) {
                        return ItemCompanyMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('itemCompanyMaster.new', {
                parent: 'itemCompanyMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-dialog.html',
                        controller: 'ItemCompanyMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    companyCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('itemCompanyMaster', null, { reload: true });
                    }, function() {
                        $state.go('itemCompanyMaster');
                    })
                }]
            })*/
            /*.state('itemCompanyMaster.edit', {
                parent: 'itemCompanyMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-dialog.html',
                        controller: 'ItemCompanyMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ItemCompanyMaster', function(ItemCompanyMaster) {
                                return ItemCompanyMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCompanyMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('itemCompanyMaster.delete', {
                parent: 'itemCompanyMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-delete-dialog.html',
                        controller: 'ItemCompanyMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ItemCompanyMaster', function(ItemCompanyMaster) {
                                return ItemCompanyMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('itemCompanyMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('itemCompanyMaster.new', {
                parent: 'itemCompanyMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCompanyMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-dialog.html',
                        controller: 'ItemCompanyMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('itemCompanyMaster.edit', {
                parent: 'itemCompanyMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ItemCompanyMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/itemCompanyMaster/itemCompanyMaster-dialog.html',
                        controller: 'ItemCompanyMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
