'use strict';

angular.module('waterERPApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('billDetails', {
                parent: 'entity',
                url: '/billDetailss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetailss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetailss.html',
                        controller: 'BillDetailsController'
                    }
                },
                resolve: {
                }
            })
            .state('billDetails.detail', {
                parent: 'entity',
                url: '/billDetails/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'BillDetails'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-detail.html',
                        controller: 'BillDetailsDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'BillDetails', function($stateParams, BillDetails) {
                        return BillDetails.get({id : $stateParams.id});
                    }]
                }
            })
            .state('billDetails.new', {
                parent: 'billDetails',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    can: null,
                                    bill_number: null,
                                    bill_date: null,
                                    bill_time: null,
                                    meter_make: null,
                                    current_bill_type: null,
                                    from_month: null,
                                    to_month: null,
                                    meter_fix_date: null,
                                    initial_reading: null,
                                    present_reading: null,
                                    units: null,
                                    water_cess: null,
                                    sewerage_cess: null,
                                    service_charge: null,
                                    meter_service_charge: null,
                                    total_amount: null,
                                    net_payable_amount: null,
                                    telephone_no: null,
                                    meter_status: null,
                                    mc_met_reader_code: null,
                                    bill_flag: null,
                                    svr_status: null,
                                    terminal_id: null,
                                    meter_reader_id: null,
                                    user_id: null,
                                    mobile_no: null,
                                    notice_no: null,
                                    lat: null,
                                    longI: null,
                                    nometer_amt: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('billDetails');
                    })
                }]
            })
            .state('billDetails.edit', {
                parent: 'billDetails',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-dialog.html',
                        controller: 'BillDetailsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['BillDetails', function(BillDetails) {
                                return BillDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('billDetails.delete', {
                parent: 'billDetails',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/billDetails/billDetails-delete-dialog.html',
                        controller: 'BillDetailsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['BillDetails', function(BillDetails) {
                                return BillDetails.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('billDetails', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
