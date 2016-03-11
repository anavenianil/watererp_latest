'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('makeOfPipe', {
                parent: 'entity',
                url: '/makeOfPipes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MakeOfPipes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/makeOfPipe/makeOfPipes.html',
                        controller: 'MakeOfPipeController'
                    }
                },
                resolve: {
                }
            })
            .state('makeOfPipe.detail', {
                parent: 'entity',
                url: '/makeOfPipe/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MakeOfPipe'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/makeOfPipe/makeOfPipe-detail.html',
                        controller: 'MakeOfPipeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MakeOfPipe', function($stateParams, MakeOfPipe) {
                        return MakeOfPipe.get({id : $stateParams.id});
                    }]
                }
            })
            .state('makeOfPipe.new', {
                parent: 'makeOfPipe',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/makeOfPipe/makeOfPipe-dialog.html',
                        controller: 'MakeOfPipeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    makeName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('makeOfPipe', null, { reload: true });
                    }, function() {
                        $state.go('makeOfPipe');
                    })
                }]
            })
            .state('makeOfPipe.edit', {
                parent: 'makeOfPipe',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/makeOfPipe/makeOfPipe-dialog.html',
                        controller: 'MakeOfPipeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MakeOfPipe', function(MakeOfPipe) {
                                return MakeOfPipe.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('makeOfPipe', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('makeOfPipe.delete', {
                parent: 'makeOfPipe',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/makeOfPipe/makeOfPipe-delete-dialog.html',
                        controller: 'MakeOfPipeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MakeOfPipe', function(MakeOfPipe) {
                                return MakeOfPipe.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('makeOfPipe', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
