'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reversalDetails', {
                parent: 'entity',
                url: '/reversalDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReversalDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reversalDetails/reversalDetailss.html',
                        controller: 'ReversalDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('reversalDetails.detail', {
                parent: 'entity',
                url: '/reversalDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReversalDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-detail.html',
                        controller: 'ReversalDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ReversalDetails', function($stateParams, ReversalDetails) {
                        return ReversalDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reversalDetails.new1', {
                parent: 'reversalDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-dialog.html',
                        controller: 'ReversalDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    cancelledDate: null,
                                    remarks: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('reversalDetails', null, { reload: true });
                    }, function() {
                        $state.go('reversalDetails');
                    })
                }]
            })
            .state('reversalDetails.edit1', {
                parent: 'reversalDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-dialog.html',
                        controller: 'ReversalDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ReversalDetails', function(ReversalDetails) {
                                return ReversalDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reversalDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('reversalDetails.delete', {
                parent: 'reversalDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-delete-dialog.html',
                        controller: 'ReversalDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ReversalDetails', function(ReversalDetails) {
                                return ReversalDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reversalDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('reversalDetails.new', {
                parent: 'reversalDetails',
                url: '/reversalDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReversalDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-dialog.html',
                        controller: 'ReversalDetailsDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('reversalDetails.edit', {
                parent: 'reversalDetails',
                url: '/edit/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ReversalDetailss'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/reversalDetails/reversalDetails-dialog.html',
                        controller: 'ReversalDetailsDialogController'
                    }
                },
                resolve: {
                }
            });
    });
