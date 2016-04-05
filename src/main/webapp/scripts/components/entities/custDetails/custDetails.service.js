'use strict';

angular.module('waterERPApp')
    .factory('CustDetails', function ($resource, DateUtils) {
        return $resource('api/custDetailss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.connDate = DateUtils.convertLocaleDateFromServer(data.connDate);
                    data.prevBillMonth = DateUtils.convertLocaleDateFromServer(data.prevBillMonth);
                    data.metReadingDt = DateUtils.convertLocaleDateFromServer(data.metReadingDt);
                    data.metReadingMo = DateUtils.convertLocaleDateFromServer(data.metReadingMo);
                    data.lastPymtDt = DateUtils.convertLocaleDateFromServer(data.lastPymtDt);
                    data.meterFixDate = DateUtils.convertLocaleDateFromServer(data.meterFixDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.prevBillMonth = DateUtils.convertLocaleDateToServer(data.prevBillMonth);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.metReadingMo = DateUtils.convertLocaleDateToServer(data.metReadingMo);
                    data.lastPymtDt = DateUtils.convertLocaleDateToServer(data.lastPymtDt);
                    data.meterFixDate = DateUtils.convertLocaleDateToServer(data.meterFixDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.connDate = DateUtils.convertLocaleDateToServer(data.connDate);
                    data.prevBillMonth = DateUtils.convertLocaleDateToServer(data.prevBillMonth);
                    data.metReadingDt = DateUtils.convertLocaleDateToServer(data.metReadingDt);
                    data.metReadingMo = DateUtils.convertLocaleDateToServer(data.metReadingMo);
                    data.lastPymtDt = DateUtils.convertLocaleDateToServer(data.lastPymtDt);
                    data.meterFixDate = DateUtils.convertLocaleDateToServer(data.meterFixDate);
                    return angular.toJson(data);
                }
            }
        });
    });
