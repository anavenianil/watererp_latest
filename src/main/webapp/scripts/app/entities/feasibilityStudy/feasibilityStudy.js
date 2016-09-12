'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('feasibilityStudy', {
                parent: 'entity',
                url: '/feasibilityStudys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudys.html',
                        controller: 'FeasibilityStudyController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudy.detail', {
                parent: 'entity',
                url: '/feasibilityStudy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudy'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-detail.html',
                        controller: 'FeasibilityStudyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'FeasibilityStudy', function($stateParams, FeasibilityStudy) {
                        return FeasibilityStudy.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('feasibilityStudy.new', {
                parent: 'feasibilityStudy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    createdDate: null,
                                    modifiedDate: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('feasibilityStudy');
                    })
                }]
            })*/
            /*.state('feasibilityStudy.edit', {
                parent: 'feasibilityStudy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['FeasibilityStudy', function(FeasibilityStudy) {
                                return FeasibilityStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('feasibilityStudy.delete', {
                parent: 'feasibilityStudy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-delete-dialog.html',
                        controller: 'FeasibilityStudyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['FeasibilityStudy', function(FeasibilityStudy) {
                                return FeasibilityStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('feasibilityStudy.new', {
                parent: 'feasibilityStudy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudy.edit', {
                parent: 'feasibilityStudy',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudyForAppTxn', {
                parent: 'feasibilityStudy',
                url: '/ForAppTxn/:applicationId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudyDetailForAppTxn', {
                parent: 'entity',
                url: '/feasibilityStudys/{applicationTxnId}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-detail.html',
                        controller: 'FeasibilityStudyDetailController'
                    }
                },
                resolve: {
                }
            });
    });
