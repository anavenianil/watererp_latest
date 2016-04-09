'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('uom', {
                parent: 'entity',
                url: '/uoms',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Uoms'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/uom/uoms.html',
                        controller: 'UomController'
                    }
                },
                resolve: {
                }
            })
            .state('uom.detail', {
                parent: 'entity',
                url: '/uom/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Uom'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/uom/uom-detail.html',
                        controller: 'UomDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Uom', function($stateParams, Uom) {
                        return Uom.get({id : $stateParams.id});
                    }]
                }
            })
            .state('uom.new', {
                parent: 'uom',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/uom/uom-dialog.html',
                        controller: 'UomDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    value: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('uom', null, { reload: true });
                    }, function() {
                        $state.go('uom');
                    })
                }]
            })
            .state('uom.edit', {
                parent: 'uom',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/uom/uom-dialog.html',
                        controller: 'UomDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Uom', function(Uom) {
                                return Uom.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('uom', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('uom.delete', {
                parent: 'uom',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/uom/uom-delete-dialog.html',
                        controller: 'UomDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Uom', function(Uom) {
                                return Uom.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('uom', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
