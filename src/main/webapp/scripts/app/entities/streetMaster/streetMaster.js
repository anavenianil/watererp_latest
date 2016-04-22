'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('streetMaster', {
                parent: 'entity',
                url: '/streetMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'StreetMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/streetMaster/streetMasters.html',
                        controller: 'StreetMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('streetMaster.detail', {
                parent: 'entity',
                url: '/streetMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'StreetMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/streetMaster/streetMaster-detail.html',
                        controller: 'StreetMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'StreetMaster', function($stateParams, StreetMaster) {
                        return StreetMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('streetMaster.new', {
                parent: 'streetMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/streetMaster/streetMaster-dialog.html',
                        controller: 'StreetMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    streetName: null,
                                    streetNo: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('streetMaster', null, { reload: true });
                    }, function() {
                        $state.go('streetMaster');
                    })
                }]
            })
            .state('streetMaster.edit', {
                parent: 'streetMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/streetMaster/streetMaster-dialog.html',
                        controller: 'StreetMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['StreetMaster', function(StreetMaster) {
                                return StreetMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('streetMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('streetMaster.delete', {
                parent: 'streetMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/streetMaster/streetMaster-delete-dialog.html',
                        controller: 'StreetMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['StreetMaster', function(StreetMaster) {
                                return StreetMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('streetMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
