'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('connectionTerminate', {
                parent: 'entity',
                url: '/connectionTerminates',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER','ROLE_CUSTOMER'],
                    pageTitle: 'ConnectionTerminates'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminates.html',
                        controller: 'ConnectionTerminateController'
                    }
                },
                resolve: {
                }
            })
            .state('connectionTerminate.detail', {
                parent: 'entity',
                url: '/connectionTerminate/{id}/{requestTypeId}',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'ConnectionTerminate'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-detail.html',
                        controller: 'ConnectionTerminateDetailController'
                    }
                },
                resolve: {
                    /*entity: ['$stateParams', 'ConnectionTerminate', function($stateParams, ConnectionTerminate) {
                        return ConnectionTerminate.get({id : $stateParams.id});
                    }]*/
                }
            })
            /*.state('connectionTerminate.new', {
                parent: 'connectionTerminate',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-dialog.html',
                        controller: 'ConnectionTerminateDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    requestDate: null,
                                    meterRecovered: null,
                                    lastMeterReading: null,
                                    meterRecoveredDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTerminate', null, { reload: true });
                    }, function() {
                        $state.go('connectionTerminate');
                    })
                }]
            })*/
            /*.state('connectionTerminate.edit', {
                parent: 'connectionTerminate',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-dialog.html',
                        controller: 'ConnectionTerminateDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ConnectionTerminate', function(ConnectionTerminate) {
                                return ConnectionTerminate.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTerminate', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('connectionTerminate.delete', {
                parent: 'connectionTerminate',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-delete-dialog.html',
                        controller: 'ConnectionTerminateDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ConnectionTerminate', function(ConnectionTerminate) {
                                return ConnectionTerminate.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('connectionTerminate', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('connectionTerminate.new', {
                parent: 'connectionTerminate',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'ConnectionTerminates'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-dialog.html',
                        controller: 'ConnectionTerminateDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('connectionTerminate.edit', {
                parent: 'connectionTerminate',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER','ROLE_CUSTOMER'],
                    pageTitle: 'ConnectionTerminates'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/connectionTerminate/connectionTerminate-dialog.html',
                        controller: 'ConnectionTerminateDialogController'
                    }
                },
                resolve: {
                }
            });
    });
