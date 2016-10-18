'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('receipt', {
                parent: 'entity',
                url: '/receipts',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipts'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/receipt/receipts.html',
                        controller: 'ReceiptController'
                    }
                },
                resolve: {
                }
            })
            .state('receipt.detail', {
                parent: 'entity',
                url: '/receipt/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipt'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/receipt/receipt-detail.html',
                        controller: 'ReceiptDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Receipt', function($stateParams, Receipt) {
                        return Receipt.get({id : $stateParams.id});
                    }]
                }
            })
            /*.state('receipt.new', {
                parent: 'receipt',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/receipt/receipt-dialog.html',
                        controller: 'ReceiptDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    amount: null,
                                    bankName: null,
                                    branchName: null,
                                    checkOrDdDate: null,
                                    checkOrDdNo: null,
                                    receiptDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('receipt', null, { reload: true });
                    }, function() {
                        $state.go('receipt');
                    })
                }]
            })*/
            /*.state('receipt.edit', {
                parent: 'receipt',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/receipt/receipt-dialog.html',
                        controller: 'ReceiptDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Receipt', function(Receipt) {
                                return Receipt.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('receipt', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })*/
            .state('receipt.delete', {
                parent: 'receipt',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/receipt/receipt-delete-dialog.html',
                        controller: 'ReceiptDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Receipt', function(Receipt) {
                                return Receipt.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('receipt', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('receipt.new', {
                parent: 'receipt',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipts'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/receipt/receipt-dialog.html',
                         controller: 'ReceiptDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('receipt.edit', {
                parent: 'receipt',
                url: '/edit/:id',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipts'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/receipt/receipt-dialog.html',
                         controller: 'ReceiptDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('receiptForAppTxn', {
                parent: 'receipt',
                url: '/receiptForAppTxn/:applicationTxnId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipt'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/receipt/receipt-dialog.html',
                        controller: 'ReceiptDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('receiptDetailByApplicationTxn', {
                parent: 'entity',
                url: '/receiptForApplicationTxn/{applicationTxnId}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipt'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/receipt/receipt-detail.html',
                        controller: 'ReceiptDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Receipt', function($stateParams, Receipt) {
                        return Receipt.getByApplicationTxn({applicationTxnId : $stateParams.applicationTxnId});
                    }]
                }
            })
            .state('receiptForAll', {
                parent: 'receipt',
                url: '/sewerageReceipt',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipts'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/receipt/receiptAll-dialog.html',
                         controller: 'ReceiptAllDialogController'
                    }
                },
                resolve: {
                }
            })
            .state('receiptForSewerage', {
                parent: 'receipt',
                url: '/sewerageReceipt/{sewerageRequestId}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Receipts'
                },
                views: {
                    'content@': {
                    	 templateUrl: 'scripts/app/entities/receipt/receiptForSewerage-dialog.html',
                         controller: 'ReceiptForSewerageDialogController'
                    }
                },
                resolve: {
                }
            });
    });
