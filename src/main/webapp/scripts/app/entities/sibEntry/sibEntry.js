'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('sibEntry', {
                parent: 'entity',
                url: '/sibEntrys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SibEntrys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sibEntry/sibEntrys.html',
                        controller: 'SibEntryController'
                    }
                },
                resolve: {
                }
            })
            .state('sibEntry.detail', {
                parent: 'entity',
                url: '/sibEntry/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SibEntry'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sibEntry/sibEntry-detail.html',
                        controller: 'SibEntryDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SibEntry', function($stateParams, SibEntry) {
                        return SibEntry.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('sibEntry.new', {
                parent: 'sibEntry',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sibEntry/sibEntry-dialog.html',
                        controller: 'SibEntryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    sibId: null,
                                    soNo: null,
                                    soDate: null,
                                    demandDate: null,
                                    dir: null,
                                    divName: null,
                                    invNo: null,
                                    sibDate: null,
                                    sibNo: null,
                                    irDate: null,
                                    irNo: null,
                                    vendorCode: null,
                                    remarks: null,
                                    toUser: null,
                                    fromUser: null,
                                    status: null,
                                    creationDate: null,
                                    lastModifiedDate: null,
                                    dcNo: null,
                                    dcDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('sibEntry', null, { reload: true });
                    }, function() {
                        $state.go('sibEntry');
                    })
                }]
            })*/
            /*.state('sibEntry.edit', {
                parent: 'sibEntry',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sibEntry/sibEntry-dialog.html',
                        controller: 'SibEntryDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['SibEntry', function(SibEntry) {
                                return SibEntry.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sibEntry', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('sibEntry.delete', {
                parent: 'sibEntry',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sibEntry/sibEntry-delete-dialog.html',
                        controller: 'SibEntryDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SibEntry', function(SibEntry) {
                                return SibEntry.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sibEntry', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('sibEntry.new', {
                parent: 'sibEntry',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SibEntrys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/sibEntry/sibEntry-dialog.html',
                        controller: 'SibEntryDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('sibEntry.edit', {
                parent: 'sibEntry',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SibEntrys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/sibEntry/sibEntry-dialog.html',
                        controller: 'SibEntryDialogController'
                    }
                },
                resolve: {
                }
            });
    });
