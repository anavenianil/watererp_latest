'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('applicationTypeMaster', {
                parent: 'entity',
                url: '/applicationTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTypeMaster/applicationTypeMasters.html',
                        controller: 'ApplicationTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('applicationTypeMaster.detail', {
                parent: 'entity',
                url: '/applicationTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTypeMaster/applicationTypeMaster-detail.html',
                        controller: 'ApplicationTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ApplicationTypeMaster', function($stateParams, ApplicationTypeMaster) {
                        return ApplicationTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('applicationTypeMaster.new', {
                parent: 'applicationTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTypeMaster/applicationTypeMaster-dialog.html',
                        controller: 'ApplicationTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    applicationType: null,
                                    createdDate: null,
                                    updatedDate: null,
                                    status: null,
                                    createdBy: null,
                                    updatedBy: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('applicationTypeMaster');
                    })
                }]
            })
            .state('applicationTypeMaster.edit', {
                parent: 'applicationTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTypeMaster/applicationTypeMaster-dialog.html',
                        controller: 'ApplicationTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ApplicationTypeMaster', function(ApplicationTypeMaster) {
                                return ApplicationTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('applicationTypeMaster.delete', {
                parent: 'applicationTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTypeMaster/applicationTypeMaster-delete-dialog.html',
                        controller: 'ApplicationTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ApplicationTypeMaster', function(ApplicationTypeMaster) {
                                return ApplicationTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
