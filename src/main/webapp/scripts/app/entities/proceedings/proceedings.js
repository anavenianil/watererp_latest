'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('proceedings', {
                parent: 'entity',
                url: '/proceedingss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedingss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proceedings/proceedingss.html',
                        controller: 'ProceedingsController'
                    }
                },
                resolve: {
                }
            })
            .state('proceedings.detail', {
                parent: 'entity',
                url: '/proceedings/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proceedings/proceedings-detail.html',
                        controller: 'ProceedingsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Proceedings', function($stateParams, Proceedings) {
                        return Proceedings.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('proceedings.new', {
                parent: 'proceedings',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proceedings/proceedings-dialog.html',
                        controller: 'ProceedingsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    subTotalA: null,
                                    supervisionCharge: null,
                                    labourCharge: null,
                                    siteSurvey: null,
                                    subTotalB: null,
                                    connectionFee: null,
                                    waterMeterShs: null,
                                    applicationFormFee: null,
                                    grandTotal: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('proceedings', null, { reload: true });
                    }, function() {
                        $state.go('proceedings');
                    })
                }]
            })*/
            /*.state('proceedings.edit', {
                parent: 'proceedings',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proceedings/proceedings-dialog.html',
                        controller: 'ProceedingsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Proceedings', function(Proceedings) {
                                return Proceedings.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('proceedings', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('proceedings.delete', {
                parent: 'proceedings',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proceedings/proceedings-delete-dialog.html',
                        controller: 'ProceedingsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Proceedings', function(Proceedings) {
                                return Proceedings.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('proceedings', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('proceedings.new', {
                parent: 'proceedings',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedingss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/proceedings/proceedings-dialog.html',
                        controller: 'ProceedingsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('proceedings.edit', {
                parent: 'proceedings',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedingss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/proceedings/proceedings-dialog.html',
                        controller: 'ProceedingsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('proceedingsForAppTxn', {
                parent: 'proceedings',
                url: '/ForAppTxn/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedings'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/proceedings/proceedings-dialog.html',
                        controller: 'ProceedingsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('proceedingsDetailForAppTxn', {
                parent: 'proceedings',
                url: '/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Proceedings'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proceedings/proceedings-detail.html',
                        controller: 'ProceedingsDetailController'
                    }
                },
                resolve: {
                }
            });
    });
