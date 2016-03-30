'use strict';

angular.module('watererpApp')
    .controller('SibEntryController', function ($scope, $state, SibEntry, ParseLinks) {

        $scope.sibEntrys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            SibEntry.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.sibEntrys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.sibEntrys = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.sibEntry = {
                sibId: null,
                soNo: null,
                soDate: null,
                demandDate: null,
                dir: null,
                divName: null,
                invNo: null,
                sibDate: null,
                sibNo: null,
                irDate: null,
                irNo: null,
                vendorCode: null,
                remarks: null,
                toUser: null,
                fromUser: null,
                status: null,
                creationDate: null,
                lastModifiedDate: null,
                dcNo: null,
                dcDate: null,
                id: null
            };
        };
    });
