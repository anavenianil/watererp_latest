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
