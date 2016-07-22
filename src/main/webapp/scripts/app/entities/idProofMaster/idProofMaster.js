'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('idProofMaster', {
                parent: 'entity',
                url: '/idProofMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'IdProofMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/idProofMaster/idProofMasters.html',
                        controller: 'IdProofMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('idProofMaster.detail', {
                parent: 'entity',
                url: '/idProofMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'IdProofMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/idProofMaster/idProofMaster-detail.html',
                        controller: 'IdProofMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'IdProofMaster', function($stateParams, IdProofMaster) {
                        return IdProofMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('idProofMaster.new', {
                parent: 'idProofMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/idProofMaster/idProofMaster-dialog.html',
                        controller: 'IdProofMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    idProof: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('idProofMaster', null, { reload: true });
                    }, function() {
                        $state.go('idProofMaster');
                    })
                }]
            })
            .state('idProofMaster.edit', {
                parent: 'idProofMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/idProofMaster/idProofMaster-dialog.html',
                        controller: 'IdProofMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['IdProofMaster', function(IdProofMaster) {
                                return IdProofMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('idProofMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('idProofMaster.delete', {
                parent: 'idProofMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/idProofMaster/idProofMaster-delete-dialog.html',
                        controller: 'IdProofMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['IdProofMaster', function(IdProofMaster) {
                                return IdProofMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('idProofMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
