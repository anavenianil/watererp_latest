'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('designationMaster', {
                parent: 'entity',
                url: '/designationMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesignationMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/designationMaster/designationMasters.html',
                        controller: 'DesignationMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('designationMaster.detail', {
                parent: 'entity',
                url: '/designationMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesignationMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/designationMaster/designationMaster-detail.html',
                        controller: 'DesignationMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DesignationMaster', function($stateParams, DesignationMaster) {
                        return DesignationMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('designationMaster.new', {
                parent: 'designationMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMaster/designationMaster-dialog.html',
                        controller: 'DesignationMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    description: null,
                                    orderNo: null,
                                    serviceType: null,
                                    code: null,
                                    desigalias: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('designationMaster', null, { reload: true });
                    }, function() {
                        $state.go('designationMaster');
                    })
                }]
            })
            .state('designationMaster.edit', {
                parent: 'designationMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMaster/designationMaster-dialog.html',
                        controller: 'DesignationMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DesignationMaster', function(DesignationMaster) {
                                return DesignationMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('designationMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('designationMaster.delete', {
                parent: 'designationMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMaster/designationMaster-delete-dialog.html',
                        controller: 'DesignationMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DesignationMaster', function(DesignationMaster) {
                                return DesignationMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('designationMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
