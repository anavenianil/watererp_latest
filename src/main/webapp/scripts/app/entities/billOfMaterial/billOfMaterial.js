'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billOfMaterial', {
                parent: 'entity',
                url: '/billOfMaterials',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillOfMaterials'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterials.html',
                        controller: 'BillOfMaterialController'
                    }
                },
                resolve: {
                }
            })
            .state('billOfMaterial.detail', {
                parent: 'entity',
                url: '/billOfMaterial/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillOfMaterial'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-detail.html',
                        controller: 'BillOfMaterialDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillOfMaterial', function($stateParams, BillOfMaterial) {
                        return BillOfMaterial.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('billOfMaterial.new', {
                parent: 'billOfMaterial',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-dialog.html',
                        controller: 'BillOfMaterialDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    bankName: null,
                                    branchName: null,
                                    checkOrDdDate: null,
                                    checkOrDdNo: null,
                                    billDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billOfMaterial', null, { reload: true });
                    }, function() {
                        $state.go('billOfMaterial');
                    })
                }]
            })*/
            /*.state('billOfMaterial.edit', {
                parent: 'billOfMaterial',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-dialog.html',
                        controller: 'BillOfMaterialDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillOfMaterial', function(BillOfMaterial) {
                                return BillOfMaterial.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billOfMaterial', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('billOfMaterial.delete', {
                parent: 'billOfMaterial',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-delete-dialog.html',
                        controller: 'BillOfMaterialDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillOfMaterial', function(BillOfMaterial) {
                                return BillOfMaterial.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billOfMaterial', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('billOfMaterial.new', {
                parent: 'billOfMaterial',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillOfMaterials'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-dialog.html',
                        controller: 'BillOfMaterialDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('billOfMaterial.edit', {
                parent: 'billOfMaterial',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillOfMaterials'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/billOfMaterial/billOfMaterial-dialog.html',
                        controller: 'BillOfMaterialDialogController'
                    }
                },
                resolve: {
                }
            });
    });
