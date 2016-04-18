'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('expenseDetails', {
                parent: 'entity',
                url: '/expenseDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ExpenseDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/expenseDetails/expenseDetailss.html',
                        controller: 'ExpenseDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('expenseDetails.detail', {
                parent: 'entity',
                url: '/expenseDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ExpenseDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/expenseDetails/expenseDetails-detail.html',
                        controller: 'ExpenseDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ExpenseDetails', function($stateParams, ExpenseDetails) {
                        return ExpenseDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('expenseDetails.new', {
                parent: 'expenseDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/expenseDetails/expenseDetails-dialog.html',
                        controller: 'ExpenseDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('expenseDetails.edit', {
                parent: 'expenseDetails',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/expenseDetails/expenseDetails-dialog.html',
                        controller: 'ExpenseDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('expenseDetails.delete', {
                parent: 'expenseDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/expenseDetails/expenseDetails-delete-dialog.html',
                        controller: 'ExpenseDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ExpenseDetails', function(ExpenseDetails) {
                                return ExpenseDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('expenseDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
