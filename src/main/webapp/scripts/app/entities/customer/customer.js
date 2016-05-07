'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('customer', {
                parent: 'entity',
                url: '/customers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/customer/customers.html',
                        controller: 'CustomerController'
                    }
                },
                resolve: {
                }
            })
            .state('customer.detail', {
                parent: 'entity',
                url: '/customer/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customer'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/customer/customer-detail.html',
                        controller: 'CustomerDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Customer', function($stateParams, Customer) {
                        return Customer.get({id : $stateParams.id});
                    }]
                }
            })
            .state('customer.new', {
                parent: 'customer',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customer/customer-dialog.html',
                        controller: 'CustomerDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    firstName: null,
                                    middleName: null,
                                    lastName: null,
                                    organization: null,
                                    organizationName: null,
                                    designation: null,
                                    mobileNo: null,
                                    officeNo: null,
                                    email: null,
                                    street: null,
                                    plotNo: null,
                                    blockNo: null,
                                    tanescoMeter: null,
                                    waterConnectionUse: null,
                                    bStreet: null,
                                    ward: null,
                                    bPlotNo: null,
                                    registeredMobile: null,
                                    idNumber: null,
                                    can: null,
                                    photo: null,
                                    status: null,
                                    meterReading: null,
                                    requestedDate: null,
                                    connectionDate: null,
                                    remarks: null,
                                    meterNo: null,
                                    approvedDate: null,
                                    deedDoc: null,
                                    agreementDoc: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('customer', null, { reload: true });
                    }, function() {
                        $state.go('customer');
                    })
                }]
            })
            .state('customer.edit', {
                parent: 'customer',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customer/customer-dialog.html',
                        controller: 'CustomerDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Customer', function(Customer) {
                                return Customer.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('customer', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('customer.delete', {
                parent: 'customer',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/customer/customer-delete-dialog.html',
                        controller: 'CustomerDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Customer', function(Customer) {
                                return Customer.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('customer', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('customer.categoryChange', {
                parent: 'customer',
                url: '/categoryChange',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customer'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customer/customerCategoryChange.html',
                        controller: 'CustomerCategoryChangeController'
                    }
                },
                resolve: {
                }
            })
             .state('customer.categoryChangeDetail', {
                parent: 'customer',
                url: '/categoryChange/:id/:requestTypeId',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customer'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customer/customerCategoryChange-detail.html',
                        controller: 'CustomerCategoryChangeDetailController'
                    }
                },
                resolve: {
                }
            })
            .state('customer.pipeSizeChange', {
                parent: 'customer',
                url: '/pipeSizeChange',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customers'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customer/customerPipeSizeChange.html',
                        controller: 'CustomerPipeSizeChangeController'
                    }
                },
                resolve: {
                }
            })
            .state('customer.nameChange', {
                parent: 'customer',
                url: '/nameChange',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Customers'
                },
                views: {
                    'content@': {
                    	templateUrl: 'scripts/app/entities/customer/customerNameChange.html',
                        controller: 'CustomerNameChangeController'
                    }
                },
                resolve: {
                }
            });
    });
