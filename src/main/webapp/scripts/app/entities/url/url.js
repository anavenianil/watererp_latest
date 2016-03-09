'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('url', {
                parent: 'entity',
                url: '/urls',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Urls'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/url/urls.html',
                        controller: 'UrlController'
                    }
                },
                resolve: {
                }
            })
            .state('url.detail', {
                parent: 'entity',
                url: '/url/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Url'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/url/url-detail.html',
                        controller: 'UrlDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Url', function($stateParams, Url) {
                        return Url.get({id : $stateParams.id});
                    }]
                }
            })
            .state('url.new', {
                parent: 'url',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url/url-dialog.html',
                        controller: 'UrlDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    urlPattern: null,
                                    version: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('url', null, { reload: true });
                    }, function() {
                        $state.go('url');
                    })
                }]
            })
            .state('url.edit', {
                parent: 'url',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url/url-dialog.html',
                        controller: 'UrlDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Url', function(Url) {
                                return Url.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('url', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('url.delete', {
                parent: 'url',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/url/url-delete-dialog.html',
                        controller: 'UrlDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Url', function(Url) {
                                return Url.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('url', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
