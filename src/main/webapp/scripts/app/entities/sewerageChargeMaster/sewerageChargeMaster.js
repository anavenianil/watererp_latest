'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('sewerageChargeMaster', {
                parent: 'entity',
                url: '/sewerageChargeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SewerageChargeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerageChargeMaster/sewerageChargeMasters.html',
                        controller: 'SewerageChargeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('sewerageChargeMaster.detail', {
                parent: 'entity',
                url: '/sewerageChargeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SewerageChargeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerageChargeMaster/sewerageChargeMaster-detail.html',
                        controller: 'SewerageChargeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SewerageChargeMaster', function($stateParams, SewerageChargeMaster) {
                        return SewerageChargeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('sewerageChargeMaster.new', {
                parent: 'sewerageChargeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageChargeMaster/sewerageChargeMaster-dialog.html',
                        controller: 'SewerageChargeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageChargeMaster', null, { reload: true });
                    }, function() {
                        $state.go('sewerageChargeMaster');
                    })
                }]
            })
            .state('sewerageChargeMaster.edit', {
                parent: 'sewerageChargeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageChargeMaster/sewerageChargeMaster-dialog.html',
                        controller: 'SewerageChargeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['SewerageChargeMaster', function(SewerageChargeMaster) {
                                return SewerageChargeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageChargeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('sewerageChargeMaster.delete', {
                parent: 'sewerageChargeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerageChargeMaster/sewerageChargeMaster-delete-dialog.html',
                        controller: 'SewerageChargeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SewerageChargeMaster', function(SewerageChargeMaster) {
                                return SewerageChargeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerageChargeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
