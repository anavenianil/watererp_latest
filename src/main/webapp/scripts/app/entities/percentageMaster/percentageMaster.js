'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('percentageMaster', {
                parent: 'entity',
                url: '/percentageMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PercentageMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/percentageMaster/percentageMasters.html',
                        controller: 'PercentageMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('percentageMaster.detail', {
                parent: 'entity',
                url: '/percentageMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'PercentageMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/percentageMaster/percentageMaster-detail.html',
                        controller: 'PercentageMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'PercentageMaster', function($stateParams, PercentageMaster) {
                        return PercentageMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('percentageMaster.new', {
                parent: 'percentageMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/percentageMaster/percentageMaster-dialog.html',
                        controller: 'PercentageMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    percentType: null,
                                    percentValue: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('percentageMaster', null, { reload: true });
                    }, function() {
                        $state.go('percentageMaster');
                    })
                }]
            })
            .state('percentageMaster.edit', {
                parent: 'percentageMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/percentageMaster/percentageMaster-dialog.html',
                        controller: 'PercentageMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['PercentageMaster', function(PercentageMaster) {
                                return PercentageMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('percentageMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('percentageMaster.delete', {
                parent: 'percentageMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/percentageMaster/percentageMaster-delete-dialog.html',
                        controller: 'PercentageMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['PercentageMaster', function(PercentageMaster) {
                                return PercentageMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('percentageMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
