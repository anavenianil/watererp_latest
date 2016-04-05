'use strict';

angular.module('waterERPApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('custMeterMapping', {
                parent: 'entity',
                url: '/custMeterMappings',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustMeterMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/custMeterMapping/custMeterMappings.html',
                        controller: 'CustMeterMappingController'
                    }
                },
                resolve: {
                }
            })
            .state('custMeterMapping.detail', {
                parent: 'entity',
                url: '/custMeterMapping/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CustMeterMapping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/custMeterMapping/custMeterMapping-detail.html',
                        controller: 'CustMeterMappingDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CustMeterMapping', function($stateParams, CustMeterMapping) {
                        return CustMeterMapping.get({id : $stateParams.id});
                    }]
                }
            })
            .state('custMeterMapping.new', {
                parent: 'custMeterMapping',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custMeterMapping/custMeterMapping-dialog.html',
                        controller: 'CustMeterMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    fromDate: null,
                                    toDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('custMeterMapping', null, { reload: true });
                    }, function() {
                        $state.go('custMeterMapping');
                    })
                }]
            })
            .state('custMeterMapping.edit', {
                parent: 'custMeterMapping',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custMeterMapping/custMeterMapping-dialog.html',
                        controller: 'CustMeterMappingDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CustMeterMapping', function(CustMeterMapping) {
                                return CustMeterMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('custMeterMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('custMeterMapping.delete', {
                parent: 'custMeterMapping',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/custMeterMapping/custMeterMapping-delete-dialog.html',
                        controller: 'CustMeterMappingDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CustMeterMapping', function(CustMeterMapping) {
                                return CustMeterMapping.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('custMeterMapping', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
