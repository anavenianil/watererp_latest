'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('complaintTypeMaster', {
                parent: 'entity',
                url: '/complaintTypeMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ComplaintTypeMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMasters.html',
                        controller: 'ComplaintTypeMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('complaintTypeMaster.detail', {
                parent: 'entity',
                url: '/complaintTypeMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ComplaintTypeMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-detail.html',
                        controller: 'ComplaintTypeMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ComplaintTypeMaster', function($stateParams, ComplaintTypeMaster) {
                        return ComplaintTypeMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('complaintTypeMaster.new', {
                parent: 'complaintTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ComplaintTypeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-dialog.html',
                        controller: 'ComplaintTypeMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('complaintTypeMaster.edit', {
                parent: 'complaintTypeMaster',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ComplaintTypeMasters'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-dialog.html',
                        controller: 'ComplaintTypeMasterDialogController'
                    }
                },
                resolve: {
                }
            })
            /*.state('complaintTypeMaster.new', {
                parent: 'complaintTypeMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-dialog.html',
                        controller: 'ComplaintTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    complaintType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('complaintTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('complaintTypeMaster');
                    })
                }]
            })
            .state('complaintTypeMaster.edit', {
                parent: 'complaintTypeMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-dialog.html',
                        controller: 'ComplaintTypeMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ComplaintTypeMaster', function(ComplaintTypeMaster) {
                                return ComplaintTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('complaintTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('complaintTypeMaster.delete', {
                parent: 'complaintTypeMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/complaintTypeMaster/complaintTypeMaster-delete-dialog.html',
                        controller: 'ComplaintTypeMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ComplaintTypeMaster', function(ComplaintTypeMaster) {
                                return ComplaintTypeMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('complaintTypeMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
