'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('fileNumber', {
                parent: 'entity',
                url: '/fileNumbers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FileNumbers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fileNumber/fileNumbers.html',
                        controller: 'FileNumberController'
                    }
                },
                resolve: {
                }
            })
            .state('fileNumber.detail', {
                parent: 'entity',
                url: '/fileNumber/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FileNumber'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fileNumber/fileNumber-detail.html',
                        controller: 'FileNumberDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'FileNumber', function($stateParams, FileNumber) {
                        return FileNumber.get({id : $stateParams.id});
                    }]
                }
            })
            .state('fileNumber.new', {
                parent: 'fileNumber',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileNumber/fileNumber-dialog.html',
                        controller: 'FileNumberDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    fileNo: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('fileNumber', null, { reload: true });
                    }, function() {
                        $state.go('fileNumber');
                    })
                }]
            })
            .state('fileNumber.edit', {
                parent: 'fileNumber',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileNumber/fileNumber-dialog.html',
                        controller: 'FileNumberDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['FileNumber', function(FileNumber) {
                                return FileNumber.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('fileNumber', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('fileNumber.delete', {
                parent: 'fileNumber',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileNumber/fileNumber-delete-dialog.html',
                        controller: 'FileNumberDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['FileNumber', function(FileNumber) {
                                return FileNumber.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('fileNumber', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
