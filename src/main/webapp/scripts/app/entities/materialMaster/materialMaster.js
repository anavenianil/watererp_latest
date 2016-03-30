'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('materialMaster', {
                parent: 'entity',
                url: '/materialMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MaterialMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/materialMaster/materialMasters.html',
                        controller: 'MaterialMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('materialMaster.detail', {
                parent: 'entity',
                url: '/materialMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MaterialMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/materialMaster/materialMaster-detail.html',
                        controller: 'MaterialMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MaterialMaster', function($stateParams, MaterialMaster) {
                        return MaterialMaster.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('materialMaster.new', {
                parent: 'materialMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/materialMaster/materialMaster-dialog.html',
                        controller: 'MaterialMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    materialName: null,
                                    consumableFlag: null,
                                    uomId: null,
                                    categoryId: null,
                                    subCategoryId: null,
                                    itemCodeId: null,
                                    itemSubCodeId: null,
                                    rateContractFlag: null,
                                    unitRate: null,
                                    description: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    companyCodeId: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('materialMaster', null, { reload: true });
                    }, function() {
                        $state.go('materialMaster');
                    })
                }]
            })*/
            /*.state('materialMaster.edit', {
                parent: 'materialMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/materialMaster/materialMaster-dialog.html',
                        controller: 'MaterialMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MaterialMaster', function(MaterialMaster) {
                                return MaterialMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('materialMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('materialMaster.delete', {
                parent: 'materialMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/materialMaster/materialMaster-delete-dialog.html',
                        controller: 'MaterialMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MaterialMaster', function(MaterialMaster) {
                                return MaterialMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('materialMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('materialMaster.new', {
                parent: 'materialMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MaterialMasters'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/materialMaster/materialMaster-dialog.html',
                         controller: 'MaterialMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('materialMaster.edit', {
                parent: 'materialMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MaterialMasters'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/materialMaster/materialMaster-dialog.html',
                         controller: 'MaterialMasterDialogController'
                    }
                },
                resolve: {
                }
            });
    });
