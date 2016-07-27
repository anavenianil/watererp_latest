'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
        .state('revDetails', {
            parent: 'entity',
            url: '/revDetails/new',
            data: {
                authorities: ['ROLE_CASHIER','ROLE_USER'],
                pageTitle: 'Revenue Details'
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/entities/collDetails/revDetails-dialog.html',
                    controller: 'RevDetailsDialogController'
                }
            },
            resolve: {
            }
        })
        .state('revDetails.detail', {
                parent: 'entity',
                url: '/revDetails/{id}',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'CollDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/revDetails-detail.html',
                        controller: 'CollDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CollDetails', function($stateParams, CollDetails) {
                        return CollDetails.get({id : $stateParams.id});
                    }]
                }
            })
        .state('revDetailss', {
                parent: 'entity',
                url: '/revDetails',
                data: {
                    authorities: ['ROLE_CASHIER','ROLE_USER'],
                    pageTitle: 'Revenue Details'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/collDetails/revDetailss.html',
                        controller: 'RevDetailsController' 
                    }
                },
                resolve: {
                }
            })
    });
