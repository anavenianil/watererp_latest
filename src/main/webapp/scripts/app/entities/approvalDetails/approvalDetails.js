'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('approvalDetails', {
                parent: 'entity',
                url: '/approvalDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApprovalDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/approvalDetails/approvalDetailss.html',
                        controller: 'ApprovalDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('approvalDetails.detail', {
                parent: 'entity',
                url: '/approvalDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ApprovalDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/approvalDetails/approvalDetails-detail.html',
                        controller: 'ApprovalDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'ApprovalDetails', function($stateParams, ApprovalDetails) {
                        return ApprovalDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('approvalDetails.new', {
                parent: 'approvalDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/approvalDetails/approvalDetails-dialog.html',
                        controller: 'ApprovalDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    remarks: null,
                                    approvedDate: null,
                                    approvedEmpNo: null,
                                    approvedEmpName: null,
                                    approvedEmpDesig: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('approvalDetails', null, { reload: true });
                    }, function() {
                        $state.go('approvalDetails');
                    })
                }]
            })
            .state('approvalDetails.edit', {
                parent: 'approvalDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/approvalDetails/approvalDetails-dialog.html',
                        controller: 'ApprovalDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ApprovalDetails', function(ApprovalDetails) {
                                return ApprovalDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('approvalDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('approvalDetails.delete', {
                parent: 'approvalDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/approvalDetails/approvalDetails-delete-dialog.html',
                        controller: 'ApprovalDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ApprovalDetails', function(ApprovalDetails) {
                                return ApprovalDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('approvalDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
