'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('designationMappings', {
                parent: 'entity',
                url: '/designationMappingss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesignationMappingss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/designationMappings/designationMappingss.html',
                        controller: 'DesignationMappingsController'
                    }
                },
                resolve: {
                }
            })
            .state('designationMappings.detail', {
                parent: 'entity',
                url: '/designationMappings/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DesignationMappings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/designationMappings/designationMappings-detail.html',
                        controller: 'DesignationMappingsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DesignationMappings', function($stateParams, DesignationMappings) {
                        return DesignationMappings.get({id : $stateParams.id});
                    }]
                }
            })
            .state('designationMappings.new', {
                parent: 'designationMappings',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMappings/designationMappings-dialog.html',
                        controller: 'DesignationMappingsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    type: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('designationMappings', null, { reload: true });
                    }, function() {
                        $state.go('designationMappings');
                    })
                }]
            })
            .state('designationMappings.edit', {
                parent: 'designationMappings',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMappings/designationMappings-dialog.html',
                        controller: 'DesignationMappingsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DesignationMappings', function(DesignationMappings) {
                                return DesignationMappings.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('designationMappings', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('designationMappings.delete', {
                parent: 'designationMappings',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/designationMappings/designationMappings-delete-dialog.html',
                        controller: 'DesignationMappingsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DesignationMappings', function(DesignationMappings) {
                                return DesignationMappings.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('designationMappings', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
