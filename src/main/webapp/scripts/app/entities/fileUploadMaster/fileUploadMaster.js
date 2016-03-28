'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('fileUploadMaster', {
                parent: 'entity',
                url: '/fileUploadMasters',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FileUploadMasters'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fileUploadMaster/fileUploadMasters.html',
                        controller: 'FileUploadMasterController'
                    }
                },
                resolve: {
                }
            })
            .state('fileUploadMaster.detail', {
                parent: 'entity',
                url: '/fileUploadMaster/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FileUploadMaster'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fileUploadMaster/fileUploadMaster-detail.html',
                        controller: 'FileUploadMasterDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'FileUploadMaster', function($stateParams, FileUploadMaster) {
                        return FileUploadMaster.get({id : $stateParams.id});
                    }]
                }
            })
            .state('fileUploadMaster.new', {
                parent: 'fileUploadMaster',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileUploadMaster/fileUploadMaster-dialog.html',
                        controller: 'FileUploadMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    photo: null,
                                    photoContentType: null,
                                    textFile: null,
                                    binaryFile: null,
                                    binaryFileContentType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('fileUploadMaster', null, { reload: true });
                    }, function() {
                        $state.go('fileUploadMaster');
                    })
                }]
            })
            .state('fileUploadMaster.edit', {
                parent: 'fileUploadMaster',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileUploadMaster/fileUploadMaster-dialog.html',
                        controller: 'FileUploadMasterDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['FileUploadMaster', function(FileUploadMaster) {
                                return FileUploadMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('fileUploadMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('fileUploadMaster.delete', {
                parent: 'fileUploadMaster',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/fileUploadMaster/fileUploadMaster-delete-dialog.html',
                        controller: 'FileUploadMasterDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['FileUploadMaster', function(FileUploadMaster) {
                                return FileUploadMaster.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('fileUploadMaster', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
