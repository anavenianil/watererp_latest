'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('subDesigCategoryMaster', {
                parent: 'entity',
                url: '/subDesigCategoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SubDesigCategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/subDesigCategoryMaster/subDesigCategoryMasters.html',
                        controller: 'SubDesigCategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('subDesigCategoryMaster.detail', {
                parent: 'entity',
                url: '/subDesigCategoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SubDesigCategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/subDesigCategoryMaster/subDesigCategoryMaster-detail.html',
                        controller: 'SubDesigCategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SubDesigCategoryMaster', function($stateParams, SubDesigCategoryMaster) {
                        return SubDesigCategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('subDesigCategoryMaster.new', {
                parent: 'subDesigCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/subDesigCategoryMaster/subDesigCategoryMaster-dialog.html',
                        controller: 'SubDesigCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    description: null,
                                    alias: null,
                                    orderBy: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('subDesigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('subDesigCategoryMaster');
                    })
                }]
            })
            .state('subDesigCategoryMaster.edit', {
                parent: 'subDesigCategoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/subDesigCategoryMaster/subDesigCategoryMaster-dialog.html',
                        controller: 'SubDesigCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['SubDesigCategoryMaster', function(SubDesigCategoryMaster) {
                                return SubDesigCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('subDesigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('subDesigCategoryMaster.delete', {
                parent: 'subDesigCategoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/subDesigCategoryMaster/subDesigCategoryMaster-delete-dialog.html',
                        controller: 'SubDesigCategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SubDesigCategoryMaster', function(SubDesigCategoryMaster) {
                                return SubDesigCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('subDesigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
