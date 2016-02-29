'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('configurationDetails', {
                parent: 'entity',
                url: '/configurationDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ConfigurationDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/configurationDetails/configurationDetailss.html',
                        controller: 'ConfigurationDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('configurationDetails.detail', {
                parent: 'entity',
                url: '/configurationDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ConfigurationDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/configurationDetails/configurationDetails-detail.html',
                        controller: 'ConfigurationDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ConfigurationDetails', function($stateParams, ConfigurationDetails) {
                        return ConfigurationDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('configurationDetails.new', {
                parent: 'configurationDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/configurationDetails/configurationDetails-dialog.html',
                        controller: 'ConfigurationDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    value: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('configurationDetails', null, { reload: true });
                    }, function() {
                        $state.go('configurationDetails');
                    })
                }]
            })
            .state('configurationDetails.edit', {
                parent: 'configurationDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/configurationDetails/configurationDetails-dialog.html',
                        controller: 'ConfigurationDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ConfigurationDetails', function(ConfigurationDetails) {
                                return ConfigurationDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('configurationDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('configurationDetails.delete', {
                parent: 'configurationDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/configurationDetails/configurationDetails-delete-dialog.html',
                        controller: 'ConfigurationDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ConfigurationDetails', function(ConfigurationDetails) {
                                return ConfigurationDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('configurationDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
