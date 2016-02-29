'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('requestMaster', {
                parent: 'entity',
                url: '/requestMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestMaster/requestMasters.html',
                        controller: 'RequestMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('requestMaster.detail', {
                parent: 'entity',
                url: '/requestMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RequestMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/requestMaster/requestMaster-detail.html',
                        controller: 'RequestMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'RequestMaster', function($stateParams, RequestMaster) {
                        return RequestMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('requestMaster.new', {
                parent: 'requestMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestMaster/requestMaster-dialog.html',
                        controller: 'RequestMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    requestType: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    description: null,
                                    internalFlag: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('requestMaster', null, { reload: true });
                    }, function() {
                        $state.go('requestMaster');
                    })
                }]
            })
            .state('requestMaster.edit', {
                parent: 'requestMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestMaster/requestMaster-dialog.html',
                        controller: 'RequestMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RequestMaster', function(RequestMaster) {
                                return RequestMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('requestMaster.delete', {
                parent: 'requestMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/requestMaster/requestMaster-delete-dialog.html',
                        controller: 'RequestMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RequestMaster', function(RequestMaster) {
                                return RequestMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('requestMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
