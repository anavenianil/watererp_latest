'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('collDetails', {
                parent: 'entity',
                url: '/collDetailss',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/collDetailss.html',
                        controller: 'CollDetailsController' 
                    }
                },
                resolve: {
                }
            })
            .state('collDetails1', {
                parent: 'entity',
                url: '/collDetailss',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/revDetailss.html',
                        controller: 'CollDetailsController' 
                    }
                },
                resolve: {
                }
            })
            .state('collDetails.detail', {
                parent: 'entity',
                url: '/collDetails/{id}',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-detail.html',
                        controller: 'CollDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CollDetails', function($stateParams, CollDetails) {
                        return CollDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('collDetails.new', {
                parent: 'collDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/collDetails/collDetails-dialog.html',
                        controller: 'CollDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('collDetails.edit', {
                parent: 'collDetails',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/collDetails/collDetails-dialog.html',
                        controller: 'CollDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('collDetails.delete', {
                parent: 'collDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/collDetails/collDetails-delete-dialog.html',
                        controller: 'CollDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CollDetails', function(CollDetails) {
                                return CollDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('collDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            }) 
            
            .state('collDetails.cancel', {
                parent: 'collDetails',
                url: '/cancel',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/collDetails/collDetailsCancel-dialog.html',
                        controller: 'CollDetailsCancelDialogController'
                    }
                },
                resolve: {
                }
            })
        
        .state('collectionDetailsYearlyReport1', {
            parent: 'collDetails',
            url: '/collectionDetailsYearlyReport',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'collDetailss'
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/entities/collDetails/collectionDetailsYearlyReport.html',
                    controller: 'CollectionDetailsYearlyReportController'
                }
            },
            resolve: {
            }
        })
        
         .state('collectionDetailsYearlyReport', {
                parent: 'collDetails',
                url: '/collectionDetailsYearlyReport',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CollDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/collDetails/collectionDetailsYearlyReport.html',
                        controller: 'CollectionDetailsYearlyReportController'
                    }
                },
                resolve: {
                }
            });
           
    });
