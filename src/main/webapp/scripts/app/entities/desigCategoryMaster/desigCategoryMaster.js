'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('desigCategoryMaster', {
                parent: 'entity',
                url: '/desigCategoryMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesigCategoryMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/desigCategoryMaster/desigCategoryMasters.html',
                        controller: 'DesigCategoryMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('desigCategoryMaster.detail', {
                parent: 'entity',
                url: '/desigCategoryMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesigCategoryMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/desigCategoryMaster/desigCategoryMaster-detail.html',
                        controller: 'DesigCategoryMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DesigCategoryMaster', function($stateParams, DesigCategoryMaster) {
                        return DesigCategoryMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('desigCategoryMaster.new', {
                parent: 'desigCategoryMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/desigCategoryMaster/desigCategoryMaster-dialog.html',
                        controller: 'DesigCategoryMasterDialogController',
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
                        $state.go('desigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('desigCategoryMaster');
                    })
                }]
            })
            .state('desigCategoryMaster.edit', {
                parent: 'desigCategoryMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/desigCategoryMaster/desigCategoryMaster-dialog.html',
                        controller: 'DesigCategoryMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DesigCategoryMaster', function(DesigCategoryMaster) {
                                return DesigCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('desigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('desigCategoryMaster.delete', {
                parent: 'desigCategoryMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/desigCategoryMaster/desigCategoryMaster-delete-dialog.html',
                        controller: 'DesigCategoryMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DesigCategoryMaster', function(DesigCategoryMaster) {
                                return DesigCategoryMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('desigCategoryMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
