'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'MainController'
                    }
                },
                resolve: {
                    
                }
            })
            .state('request', {
            	parent : 'site',
            	url : '/request/:type/:action_type', //type = 'Pending','MyRequest', 'Approved'
            	data : {
            		roles : []
            	},
            	views : {
            		'content@' : {
            			templateUrl : 'scripts/app/main/request.html',
            			controller : 'RequestController'
            		}
            	},
            	resolve : {

            	}
            });
    });
