'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('docketCode', {
                parent: 'entity',
                url: '/docketCodes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DocketCodes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/docketCode/docketCodes.html',
                        controller: 'DocketCodeController'
                    }
                },
                resolve: {
                }
            })
            .state('docketCode.detail', {
                parent: 'entity',
                url: '/docketCode/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DocketCode'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/docketCode/docketCode-detail.html',
                        controller: 'DocketCodeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DocketCode', function($stateParams, DocketCode) {
                        return DocketCode.get({id : $stateParams.id});
                    }]
                }
            })
            .state('docketCode.new', {
                parent: 'docketCode',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/docketCode/docketCode-dialog.html',
                        controller: 'DocketCodeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    code: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('docketCode', null, { reload: true });
                    }, function() {
                        $state.go('docketCode');
                    })
                }]
            })
            .state('docketCode.edit', {
                parent: 'docketCode',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/docketCode/docketCode-dialog.html',
                        controller: 'DocketCodeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DocketCode', function(DocketCode) {
                                return DocketCode.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('docketCode', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('docketCode.delete', {
                parent: 'docketCode',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/docketCode/docketCode-delete-dialog.html',
                        controller: 'DocketCodeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DocketCode', function(DocketCode) {
                                return DocketCode.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('docketCode', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
