'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('meterChange', {
                parent: 'entity',
                url: '/meterChanges',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'MeterChanges'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterChange/meterChanges.html',
                        controller: 'MeterChangeController'
                    }
                },
                resolve: {
                }
            })
            .state('meterChange.detail', {
                parent: 'entity',
                url: '/meterChange/{id}/{requestTypeId}',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'MeterChange'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meterChange/meterChange-detail.html',
                        controller: 'MeterChangeDetailController'
                    }
                },
                resolve: {
                    /*entity: ['$stateParams', 'MeterChange', function($stateParams, MeterChange) {
                        return MeterChange.get({id : $stateParams.id});
                    }]*/
                }
            })
            /*.state('meterChange.new', {
                parent: 'meterChange',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterChange/meterChange-dialog.html',
                        controller: 'MeterChangeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    reasonForChange: null,
                                    existingMeterNumber: null,
                                    existingMeterReading: null,
                                    newMeterNumber: null,
                                    newMeterReading: null,
                                    remarks: null,
                                    approvedDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('meterChange', null, { reload: true });
                    }, function() {
                        $state.go('meterChange');
                    })
                }]
            })*/
            /*.state('meterChange.edit', {
                parent: 'meterChange',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterChange/meterChange-dialog.html',
                        controller: 'MeterChangeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MeterChange', function(MeterChange) {
                                return MeterChange.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterChange', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('meterChange.delete', {
                parent: 'meterChange',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meterChange/meterChange-delete-dialog.html',
                        controller: 'MeterChangeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['MeterChange', function(MeterChange) {
                                return MeterChange.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meterChange', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('meterChange.new', {
                parent: 'meterChange',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'MeterChanges'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/meterChange/meterChange-dialog.html',
                        controller: 'MeterChangeDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('meterChange.edit', {
                parent: 'meterChange',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'MeterChanges'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/meterChange/meterChange-dialog.html',
                        controller: 'MeterChangeDialogController'
                    }
                },
                resolve: {
                }
            });
    });
