'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('zoneMaster', {
                parent: 'entity',
                url: '/zoneMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ZoneMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/zoneMaster/zoneMasters.html',
                        controller: 'ZoneMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('zoneMaster.detail', {
                parent: 'entity',
                url: '/zoneMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ZoneMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/zoneMaster/zoneMaster-detail.html',
                        controller: 'ZoneMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ZoneMaster', function($stateParams, ZoneMaster) {
                        return ZoneMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('zoneMaster.new', {
                parent: 'zoneMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/zoneMaster/zoneMaster-dialog.html',
                        controller: 'ZoneMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    zoneName: null,
                                    zoneCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('zoneMaster', null, { reload: true });
                    }, function() {
                        $state.go('zoneMaster');
                    })
                }]
            })
            .state('zoneMaster.edit', {
                parent: 'zoneMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/zoneMaster/zoneMaster-dialog.html',
                        controller: 'ZoneMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ZoneMaster', function(ZoneMaster) {
                                return ZoneMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('zoneMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('zoneMaster.delete', {
                parent: 'zoneMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/zoneMaster/zoneMaster-delete-dialog.html',
                        controller: 'ZoneMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ZoneMaster', function(ZoneMaster) {
                                return ZoneMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('zoneMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
