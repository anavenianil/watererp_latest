'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('register', {
                parent: 'site',
                url: '/register',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Registration'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/register/register.html',
                        controller: 'RegisterController'
                    },
                    'dashboard@': {
                        templateUrl: 'scripts/app/account/register/register.html',
                        controller: 'RegisterController'
                    }
                },
                resolve: {
                    
                }
            });
    });
