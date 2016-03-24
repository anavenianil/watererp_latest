'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('divisionMaster', {
                parent: 'entity',
                url: '/divisionMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DivisionMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/divisionMaster/divisionMasters.html',
                        controller: 'DivisionMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('divisionMaster.detail', {
                parent: 'entity',
                url: '/divisionMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DivisionMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/divisionMaster/divisionMaster-detail.html',
                        controller: 'DivisionMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DivisionMaster', function($stateParams, DivisionMaster) {
                        return DivisionMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('divisionMaster.new', {
                parent: 'divisionMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/divisionMaster/divisionMaster-dialog.html',
                        controller: 'DivisionMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    divisionName: null,
                                    divisionCode: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('divisionMaster', null, { reload: true });
                    }, function() {
                        $state.go('divisionMaster');
                    })
                }]
            })
            .state('divisionMaster.edit', {
                parent: 'divisionMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/divisionMaster/divisionMaster-dialog.html',
                        controller: 'DivisionMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DivisionMaster', function(DivisionMaster) {
                                return DivisionMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('divisionMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('divisionMaster.delete', {
                parent: 'divisionMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/divisionMaster/divisionMaster-delete-dialog.html',
                        controller: 'DivisionMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DivisionMaster', function(DivisionMaster) {
                                return DivisionMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('divisionMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
