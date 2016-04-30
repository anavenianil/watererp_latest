'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('applicationTxn', {
                parent: 'entity',
                url: '/applicationTxns',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxns.html',
                        controller: 'ApplicationTxnController'
                    }
                },
                resolve: {
                }
            })
            .state('applicationTxn.detail', {
                parent: 'entity',
                url: '/applicationTxn/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxn'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-detail.html',
                        controller: 'ApplicationTxnDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ApplicationTxn', function($stateParams, ApplicationTxn) {
                        return ApplicationTxn.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('applicationTxn.new', {
                parent: 'applicationTxn',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    fullName: null,
                                    homeOrOficeNumber: null,
                                    regionalNumber: null,
                                    faxNumber: null,
                                    plotNumber: null,
                                    area: null,
                                    street: null,
                                    villageExecutiveOffice: null,
                                    villageExecutiveOfficeNumber: null,
                                    house: null,
                                    institution: null,
                                    business: null,
                                    industry: null,
                                    poBox: null,
                                    requestedDate: null,
                                    photo: null,
                                    fileNumber: null,
                                    createdDate: null,
                                    updatedDate: null,
                                    status: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('applicationTxn');
                    })
                }]
            })*/
            /*.state('applicationTxn.edit', {
                parent: 'applicationTxn',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ApplicationTxn', function(ApplicationTxn) {
                                return ApplicationTxn.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('applicationTxn.delete', {
                parent: 'applicationTxn',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-delete-dialog.html',
                        controller: 'ApplicationTxnDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ApplicationTxn', function(ApplicationTxn) {
                                return ApplicationTxn.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('applicationTxn', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('applicationTxn.new', {
                parent: 'applicationTxn',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('applicationTxn.edit', {
                parent: 'applicationTxn',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApplicationTxns'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/applicationTxn/applicationTxn-dialog.html',
                        controller: 'ApplicationTxnDialogController'
                    }
                },
                resolve: {
                	
                }
            })
            .state('applicationTxnIssueMeter', {
                parent: 'applicationTxn',
                url: '/issueMeter/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'IssueMeter'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/applicationTxn/issueMeter-dialog.html',
                        controller: 'IssueMeterDialogController'
                    }
                },
                resolve: {
                	
                }
            })
            .state('applicationTxnClose', {
                parent: 'applicationTxn',
                url: '/close/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'applicationTxnClose'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/applicationTxn/applicationTxnClose-dialog.html',
                        controller: 'applicationTxnCloseDialogController'
                    }
                },
                resolve: {
                	
                }
            })
             .state('applicationTxn.withoutMeter', {
                parent: 'applicationTxn',
                url: '/withoutMeter',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_CUSTOMER'],
                    pageTitle: 'ApplicationTxn'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/applicationTxn/applicationTxnWithoutMeter-dialog.html',
                        controller: 'ApplicationTxnDialogController'
                    }
                },
                resolve: {
                	
                }
            });
    });
