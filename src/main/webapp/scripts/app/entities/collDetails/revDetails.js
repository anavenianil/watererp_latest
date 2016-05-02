'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
        .state('revDetails', {
            parent: 'entity',
            url: '/revDetails/new',
            data: {
                authorities: ['ROLE_CASHIER','ROLE_USER'],
                pageTitle: 'CollDetailss'
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
    });
