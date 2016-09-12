'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jobCardItemRequirement', {
                parent: 'entity',
                url: '/jobCardItemRequirements',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobCardItemRequirements'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobCardItemRequirement/jobCardItemRequirements.html',
                        controller: 'JobCardItemRequirementController'
                    }
                },
                resolve: {
                }
            })
            .state('jobCardItemRequirement.detail', {
                parent: 'entity',
                url: '/jobCardItemRequirement/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'JobCardItemRequirement'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jobCardItemRequirement/jobCardItemRequirement-detail.html',
                        controller: 'JobCardItemRequirementDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'JobCardItemRequirement', function($stateParams, JobCardItemRequirement) {
                        return JobCardItemRequirement.get({id : $stateParams.id});
                    }]
                }
            })
            .state('jobCardItemRequirement.new', {
                parent: 'jobCardItemRequirement',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardItemRequirement/jobCardItemRequirement-dialog.html',
                        controller: 'JobCardItemRequirementDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    quantity: null,
                                    replaceLength: null,
                                    cascadeClamp: null,
                                    noOfSection: null,
                                    noOfClamps: null,
                                    type: null,
                                    domainObject: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardItemRequirement', null, { reload: true });
                    }, function() {
                        $state.go('jobCardItemRequirement');
                    })
                }]
            })
            .state('jobCardItemRequirement.edit', {
                parent: 'jobCardItemRequirement',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardItemRequirement/jobCardItemRequirement-dialog.html',
                        controller: 'JobCardItemRequirementDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['JobCardItemRequirement', function(JobCardItemRequirement) {
                                return JobCardItemRequirement.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardItemRequirement', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('jobCardItemRequirement.delete', {
                parent: 'jobCardItemRequirement',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/jobCardItemRequirement/jobCardItemRequirement-delete-dialog.html',
                        controller: 'JobCardItemRequirementDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['JobCardItemRequirement', function(JobCardItemRequirement) {
                                return JobCardItemRequirement.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jobCardItemRequirement', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
