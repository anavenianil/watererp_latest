'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('menuItem2Url', {
                parent: 'entity',
                url: '/menuItem2Urls',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuItem2Urls'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuItem2Url/menuItem2Urls.html',
                        controller: 'MenuItem2UrlController'
                    }
                },
                resolve: {
                }
            })
            .state('menuItem2Url.detail', {
                parent: 'entity',
                url: '/menuItem2Url/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'MenuItem2Url'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/menuItem2Url/menuItem2Url-detail.html',
                        controller: 'MenuItem2UrlDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'MenuItem2Url', function($stateParams, MenuItem2Url) {
                        return MenuItem2Url.get({id : $stateParams.id});
                    }]
                }
            })
            .state('menuItem2Url.new', {
                parent: 'menuItem2Url',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem2Url/menuItem2Url-dialog.html',
                        controller: 'MenuItem2UrlDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem2Url', null, { reload: true });
                    }, function() {
                        $state.go('menuItem2Url');
                    })
                }]
            })
            .state('menuItem2Url.edit', {
                parent: 'menuItem2Url',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem2Url/menuItem2Url-dialog.html',
                        controller: 'MenuItem2UrlDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MenuItem2Url', function(MenuItem2Url) {
                                return MenuItem2Url.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem2Url', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('menuItem2Url.delete', {
                parent: 'menuItem2Url',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/menuItem2Url/menuItem2Url-delete-dialog.html',
                        controller: 'MenuItem2UrlDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MenuItem2Url', function(MenuItem2Url) {
                                return MenuItem2Url.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('menuItem2Url', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
