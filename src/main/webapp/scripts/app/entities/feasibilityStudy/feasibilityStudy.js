'use strict';

angular.module('watererpApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('feasibilityStudy', {
                parent: 'entity',
                url: '/feasibilityStudys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudys.html',
                        controller: 'FeasibilityStudyController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudy.detail', {
                parent: 'entity',
                url: '/feasibilityStudy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudy'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-detail.html',
                        controller: 'FeasibilityStudyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'FeasibilityStudy', function($stateParams, FeasibilityStudy) {
                        return FeasibilityStudy.get({id : $stateParams.id});
                    }]
                }
            })
            .state('feasibilityStudy.new', {
                parent: 'feasibilityStudy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    plotAreaInSqMtrs: null,
                                    plotAreaInYards: null,
                                    noOfFlatsOrNoOfUnits: null,
                                    noOfFloors: null,
                                    totalPlinthArea: null,
                                    waterRequirement: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('feasibilityStudy');
                    })
                }]
            })
            .state('feasibilityStudy1.edit', {
                parent: 'feasibilityStudy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-dialog.html',
                        controller: 'FeasibilityStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['FeasibilityStudy', function(FeasibilityStudy) {
                                return FeasibilityStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('feasibilityStudy.delete', {
                parent: 'feasibilityStudy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudy-delete-dialog.html',
                        controller: 'FeasibilityStudyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['FeasibilityStudy', function(FeasibilityStudy) {
                                return FeasibilityStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('feasibilityStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('feasibilityStudy.form', {
                parent: 'feasibilityStudy',
                url: '/form',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'FeasibilityStudysForm'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudysForm.html',
                        controller: 'FeasibilityStudyFormController'
                    }
                },
                resolve: {
                }
            })
            .state('feasibilityStudy.edit',	{
            	parent : 'feasibilityStudy',
            	url : '/edit/:feasibilityStudyId',
            	//url: '/form',
				data : {
					authorities : [ 'ROLE_USER' ],
					pageTitle : 'FeasibilityStudyForm'
				},
				views : {
					'content@' : {
						templateUrl: 'scripts/app/entities/feasibilityStudy/feasibilityStudysForm.html',
                        controller: 'FeasibilityStudyFormController'
					}
				},
				resolve : {}
				});
    });
