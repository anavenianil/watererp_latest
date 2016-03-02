'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('sewerSize', {
                parent: 'entity',
                url: '/sewerSizes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SewerSizes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSizes.html',
                        controller: 'SewerSizeController'
                    }
                },
                resolve: {
                }
            })
            /*.state('sewerSize.edit', {
            	parent: 'sewerSize',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSizes.html',
                        controller: 'SewerSizeController'
                    }
                },
                resolve: {
                    entity: ['SewerSize', function(SewerSize) {
                        return SewerSize.get({id : $stateParams.id});
                    }]
                }
            })*/
            .state('sewerSize.detail', {
                parent: 'entity',
                url: '/sewerSize/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'SewerSize'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSize-detail.html',
                        controller: 'SewerSizeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'SewerSize', function($stateParams, SewerSize) {
                        return SewerSize.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('sewerSize.new', {
                parent: 'sewerSize',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$uibModal', function($uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSize-dialog.html',
                        controller: 'SewerSizeDialogController',
                        size: 'lg',
                        resolve: {
                 
                        }
                    })
                 }]
            })*/
            /*.state('sewerSize.edit1', {
                parent: 'sewerSize',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSizes.html',
                        controller: 'SewerSizeController',
                        size: 'lg',
                        resolve: {
                            entity: ['SewerSize', function(SewerSize) {
                                return SewerSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerSize', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('sewerSize.delete', {
                parent: 'sewerSize',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/sewerSize/sewerSize-delete-dialog.html',
                        controller: 'SewerSizeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['SewerSize', function(SewerSize) {
                            	$('#viewSewerSizeModal').modal('hide');
                                return SewerSize.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('sewerSize', null, { reload: true });
                        
                    }, function() {
                        $state.go('^');
                        $('#viewSewerSizeModal').modal('hide');
                    })
                }]
            });
    });
